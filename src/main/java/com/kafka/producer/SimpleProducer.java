package com.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {

    private static String BOOTSTRAP_SERVERS = "localhost:9092";
    private static String TOPIC_NAME = "vv-kafka-producer-topic";



    public static void main(String args[]){

        // Create configuration options for our producer and initialize a new producer
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        // We configure the serializer to describe the format in which we want to produce data into
        // our Kafka cluster
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Since we need to close our producer, we can use the try-with-resources statement to
        // create
        // a new producer
        try (org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props)) {
            // here, we run an infinite loop to sent a message to the cluster every second
            for (int i = 0;; i++) {
                String key = Integer.toString(i);
                String message = "this is message " + Integer.toString(i);

                producer.send(new ProducerRecord<String, String>(TOPIC_NAME, key, message));

                // log a confirmation once the message is written
                System.out.println("sent msg " + key);
                try {
                    // Sleep for a second
                    Thread.sleep(1000);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not start producer: " + e);
        }

    }
}
