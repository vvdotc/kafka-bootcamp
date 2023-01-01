package vv.cisco.kafka.poc.kafka.main;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SSLConsumer
{
  public static void main(String[] args)
  {
    Properties props = new Properties();
    
    props.put("bootstrap.servers", "eds-kafka-prd-01:9093,eds-kafka-prd-02:9093,eds-kafka-prd-03:9093,eds-kafka-prd-04:9093,eds-kafka-prd-05:9093,eds-kafka-prd-06:9093,eds-kafka-prd-07:9093,eds-kafka-prd-08:9093");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
    
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("group.id", "edsadmin_consumer_group");
    
    props.put("ssl.protocol", "SSL");
    props.put("security.protocol", "SSL");
    props.put("ssl.truststore.location", "/users/hdpccwr/kafkassl/kafka.client.truststore.jks");
    props.put("ssl.truststore.password", "hdpccwr");
    props.put("ssl.keystore.location", "/users/hdpccwr/kafkassl/kafka.client.keystore.jks");
    props.put("ssl.keystore.password", "hdpccwr");
    props.put("ssl.key.password", "hdpccwr");
    props.put("ssl.key.password", "hdpccwr");
    props.put("auto.offset.reset", "earliest");
    
    KafkaConsumer<String, String> consumer = new KafkaConsumer(props);
    
    consumer.subscribe(Arrays.asList(new String[] { "edsadmin" }));
    try
    {
      for (;;)
      {
        ConsumerRecords<String, String> records = consumer.poll(5000L);
        for (ConsumerRecord<String, String> record : records) {
          System.out.println("value = " + (String)record.value());
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}