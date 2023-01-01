package com.kafka.consumer;

import com.kafka.utils.ReadProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * This program reads messages from mutiple topics. Messages on "eds_services_ccwr_headers" are analyzed
 * to estimate latency (assuming clock synchronization between producer and consumer).
 *
 * Run the class using java -cp /path/xyz.jar com.kafka.consumer.JsonConsumer
 */
public class JsonConsumer {

    private static final List<String> TOPIC_LIST = Arrays.asList("vv-kafka-producer-topic");

    public static void main(String[] args) {

        String consumerPropertiesFileName = "consumer-loc.properties";
        Properties consumerProperties = ReadProperties.readProperties(consumerPropertiesFileName);

        // Kafka Consumer
        KafkaConsumer<String, byte[]> consumer;
        long startTime = System.nanoTime();
        try {
            consumer = new KafkaConsumer<String, byte[]>(consumerProperties);
            consumer.subscribe(TOPIC_LIST);

            int timeouts = 0;
            while (true) {
                // read records with a short timeout. If we time out, we don't really care.
                ConsumerRecords<String, byte[]> records = consumer.poll(200);
                if (records.count() == 0) {
                    timeouts++;
                } else {
                    System.out.printf("Got %d records after %d timeouts\n", records.count(), timeouts);
                    timeouts = 0;
                }
                for (ConsumerRecord<String, byte[]> record : records) {
                    try {
                        switch (record.topic()) {
                            case "vv-kafka-producer-topic":
                                System.out.println("***Consumed MSG :: " + record.value());
                            case "other-topic":
                                break;
                        }

                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                }


            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }


    }


    /**
     * @param value
     * @throws ClassNotFoundException
     */
    private static void deserializeMsgValue(byte[] value, String msg) throws ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(value);
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(bis);
            System.out.println("**** " + msg + " :: " + ois.readObject());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

