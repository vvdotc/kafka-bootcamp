package com.kafka.streams.hsbc;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import com.fasterxml.jackson.databind.JsonNode;

public class KafkaStreamsTest {
	
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
		
		System.out.println("***Test Kafka Streams******");
		try {
			FileReader reader=new FileReader("config/kafkaconfig.properties");
			Properties props =new Properties();
			props.load(reader);
			props.setProperty(StreamsConfig.TOPOLOGY_OPTIMIZATION, StreamsConfig.OPTIMIZE);
			
			KafkaStreamsTest jsonfilter = new KafkaStreamsTest();
			
			
			final KafkaStreams streams = new KafkaStreams(jsonfilter.createTopology(), props);
			streams.cleanUp(); // only do this in dev - not in prod
	        streams.start();

	        // print the topology
	        streams.localThreadsMetadata().forEach(data -> System.out.println(data));

	        // shutdown hook to correctly close the streams application
	        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
