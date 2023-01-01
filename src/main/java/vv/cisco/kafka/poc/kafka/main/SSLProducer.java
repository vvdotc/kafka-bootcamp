/**
 * 
 */
package vv.cisco.kafka.poc.kafka.main;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;

import vv.cisco.kafka.poc.kafka.utils.PublishJSONMsgs;

public class SSLProducer
{
  public static void main(String[] args)
    throws InterruptedException, ExecutionException
  {
      System.out.println("Program to publish simple message from Kafka producer");
      
      Properties props = new Properties();
      
      props.put("bootstrap.servers", "eds-kafka-prd-01:9093,eds-kafka-prd-02:9093,eds-kafka-prd-03:9093,eds-kafka-prd-04:9093,eds-kafka-prd-05:9093,eds-kafka-prd-06:9093,eds-kafka-prd-07:9093,eds-kafka-prd-08:9093");
      
      props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
      
      props.put("ssl.protocol", "SSL");
      props.put("security.protocol", "SSL");
      props.put("ssl.truststore.location", "/users/hdpccwr/kafkassl/kafka.client.truststore.jks");
      props.put("ssl.truststore.password", "hdpccwr");
      props.put("ssl.keystore.location", "/users/hdpccwr/kafkassl/kafka.client.keystore.jks");
      props.put("ssl.keystore.password", "hdpccwr");
      props.put("ssl.key.password", "hdpccwr");
      
      KafkaProducer<String, String> producer = new KafkaProducer(props);
      
      PublishJSONMsgs.publishQuoteHeaders(producer,"edsadmin");

  }
}
