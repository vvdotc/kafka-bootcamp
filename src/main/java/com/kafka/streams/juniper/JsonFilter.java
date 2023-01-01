package com.kafka.streams.juniper;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.Deserializer;
//import org.apache.kafka.common.utils.Bytes;
//import org.apache.kafka.common.serialization.Serializer;
//import org.apache.kafka.connect.json.JsonDeserializer;
//import org.apache.kafka.connect.json.JsonSerializer;
//import org.apache.kafka.connect.runtime.ConnectorConfig;
//import org.apache.kafka.connect.runtime.distributed.DistributedConfig;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.kstream.Materialized;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.KeyValueMapper;
//import org.apache.kafka.streams.kstream.Materialized;
//import org.apache.kafka.streams.kstream.KStreamBuilder;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.Reducer;
//import org.apache.kafka.streams.processor.AbstractProcessor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.apache.kafka.streams.state.KeyValueStore;
//import org.apache.kafka.streams.state.StoreBuilder;
//import org.apache.kafka.streams.state.Stores;

//import java.util.Arrays;
import com.fasterxml.jackson.databind.JsonNode;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
//import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;

import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;
//import java.util.concurrent.CountDownLatch;

public class JsonFilter{

	public Topology createTopology() throws Exception {
//----------------------- These JsonNode serializers are used to read data in JsonNode format------------------------------------
		final Serializer<JsonNode> jsonNodeSerializer = new JsonSerializer();
		final Deserializer<JsonNode> jsonNodeDeserializer = new JsonDeserializer();
		final Serde<JsonNode> jsonNodeSerde = Serdes.serdeFrom(jsonNodeSerializer, jsonNodeDeserializer);

		StreamsBuilder builder = new StreamsBuilder();
//------------------------Initially Reading the data from Kafka topic into KStream----------------------------------------------- 		
		KStream<String, JsonNode> Source_lookup = builder.stream("Cud_prod_reln_Topic_source_1",
				Consumed.with(Serdes.String(), jsonNodeSerde));
		KStream<String, JsonNode> Transactiondata = builder.stream("Transaction_table_Topic_source_1",
				Consumed.with(Serdes.String(), jsonNodeSerde));
		KStream<String, JsonNode> Customerdata = builder.stream("Customer_table_Topic_source_1",
				Consumed.with(Serdes.String(), jsonNodeSerde));


//------------------------Lookup-source filtering by calling the class defined in Filter.java and streaming to output k-Stream----
		KStream<String, JsonNode> AccountnumberTable = Source_lookup.map((key, value) -> {
			KeyValue<String, JsonNode> a = null;
			try {
				a = Filter.filterUCODE(key, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;
		}).filter(Filter::filterNonNull);
		AccountnumberTable.to("Cud_prod_reln_Kstream_Account_1", Produced.with(Serdes.String(), jsonNodeSerde));

		KStream<String, JsonNode> BcinTable = Source_lookup.map((key, value) -> {
			KeyValue<String, JsonNode> a = null;
			try {
				a = Filter.BCINKeyFilter(key, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;
		}).filter(Filter::filterNonNull);
		BcinTable.to("Cud_prod_reln_Kstream_Bcin_1", Produced.with(Serdes.String(), jsonNodeSerde));

		AccountnumberTable.foreach(new ForeachAction<String, JsonNode>() {
			public void apply(String key, JsonNode value) {
				System.out.println("cudprodreln-Account-number");
				System.out.println(key + ": " + value);
			}
		});

		BcinTable.foreach(new ForeachAction<String, JsonNode>() {
			public void apply(String key, JsonNode value) {
				System.out.println("cudprodreln-BCIN");
				System.out.println(key + ": " + value);
			}
		});

//------------------------------ Transaction stream filtering to pass account_number as key and jsonNode as value-----------------
//Streaming to output k-Stream
		KStream<String, JsonNode> Transactionfilter = Transactiondata.map((key, value) -> {
			KeyValue<String, JsonNode> b = null;
			try {
				b = Filter.Transaction(key, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return b;
		}).filter(Filter::filterNonNull);
		Transactionfilter.to("Transaction_table_KStream_Left_1", Produced.with(Serdes.String(), jsonNodeSerde));
		Transactionfilter.foreach(new ForeachAction<String, JsonNode>() {
			public void apply(String key, JsonNode value) {
				System.out.println("Transaction table");
				System.out.println(key + " : " + value);
			}
		});

//------------------------------ Customer stream filtering to pass account_number as key and jsonNode as value-----------------
		KStream<String, JsonNode> Customerfilter = Customerdata.map((key, value) -> {
			KeyValue<String, JsonNode> b = null;
			try {
				b = Filter.Customer(key, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception handled");
			}
			return b;
		}).filter(Filter::filterNonNull);
		Customerfilter.to("Customer_table_KStream_Left_1", Produced.with(Serdes.String(), jsonNodeSerde));
		Customerfilter.foreach(new ForeachAction<String, JsonNode>() {
			public void apply(String key, JsonNode value) {
				System.out.println("Customer table");
				System.out.println(key + " : " + value);
			}
		});		 
		return builder.build();
	}

	public static void main(String[] args) throws Exception {
		System.out.println("execution started");
		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "KSTREAM-Filter-Join-multi-GK-Table-009");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.setProperty(StreamsConfig.TOPOLOGY_OPTIMIZATION, StreamsConfig.OPTIMIZE);

		JsonFilter jsonfilter = new JsonFilter();

		// final Topology topology = builder.build();

		final KafkaStreams streams = new KafkaStreams(jsonfilter.createTopology(), config);
		streams.cleanUp(); // only do this in dev - not in prod
        streams.start();

        // print the topology
        streams.localThreadsMetadata().forEach(data -> System.out.println(data));

        // shutdown hook to correctly close the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

	}
}
