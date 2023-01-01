package com.kafka.streams.simple;

import com.kafka.utils.ReadProperties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;

import java.io.IOException;
import java.util.Properties;

public class SimpleKStreamApp {

	private static final String TOPIC_1 = "vv-kafka-producer-topic";
	private static final String K_STREAM_TARGET_TOPIC_1 = "vv-k-stream-topic-1";
	private static final String K_STREAM_TARGET_TOPIC_2 = "vv-k-stream-topic-1";

	public static void main(String[] args) throws Exception {

		String kafkaConfigFilePath = "kafka-config-loc.properties";

		System.out.println("***Test Kafka Streams******");
		try {
			Properties props = ReadProperties.readProperties(kafkaConfigFilePath);
			// props.setProperty(StreamsConfig.TOPOLOGY_OPTIMIZATION, StreamsConfig.OPTIMIZE);

			SimpleKStreamApp simpleKStreamApp = new SimpleKStreamApp();


			final KafkaStreams streams = new KafkaStreams(simpleKStreamApp.createTopology(), props);
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

	public Topology createTopology() throws Exception {
		StreamsBuilder builder = new StreamsBuilder();
		//--------Initially Reading the data from Kafka topic into KStream-----------------
		KStream<String, String> userKStream = builder.stream(TOPIC_1, Consumed.with(Serdes.String(), Serdes.String()));

		userKStream.foreach(new ForeachAction<String, String>() {
			public void apply(String key, String value) {
				System.out.println("K-Stream Data");
				System.out.println(key + " : " + value);
			}
		});


		return builder.build();
	}

}
