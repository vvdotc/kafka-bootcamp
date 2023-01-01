package com.kafka.producer;

import com.kafka.utils.GenerateJson;
import com.kafka.utils.ReadProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class JsonProducer {
    private static String TOPIC_NAME = "vv-kafka-producer-topic";

    public static void main(String args[]){

        String producer_props_file = "producer-loc.properties";
        Properties producerProperties = ReadProperties.readProperties(producer_props_file);

        // Since we need to close our producer, we can use the try-with-resources statement to
        // create a new producer
        try (Producer<String, String> producer = new KafkaProducer<>(producerProperties)) {
            // here, we run an infinite loop to sent a message to the cluster every second
            for (int i = 0;; i++) {
                String key = Integer.toString(i);
                String value = GenerateJson.generateUser(i);
                producer.send(new ProducerRecord<String, String>(TOPIC_NAME, key, value));

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

