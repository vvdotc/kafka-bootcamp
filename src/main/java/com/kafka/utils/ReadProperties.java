package com.kafka.utils;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    public static Properties readProperties(String filename){
        Properties properties = new Properties();
        try (InputStream props = Resources.getResource(filename).openStream()) {
            properties.load(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
