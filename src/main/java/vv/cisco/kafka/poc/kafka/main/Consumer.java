package vv.cisco.kafka.poc.kafka.main;

import com.google.common.io.Resources;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
 

/**
 * This program reads messages from two topics. Messages on "eds_services_ccwr_headers" are analyzed
 * to estimate latency (assuming clock synchronization between producer and consumer).
 * 
 * Run the class using java -cp /path/xyz.jar vv.poc.kafka.main.Consumer
 */
public class Consumer {
	public static void main(String[] args) throws IOException {

		// Kafka Consumer
		KafkaConsumer<String, byte[]> consumer;
		long startTime = System.nanoTime();
		try (InputStream props = Resources.getResource("cisco/consumer.props").openStream()) {
			Properties properties = new Properties();
			properties.load(props);
			if (properties.getProperty("group.id") == null) {
				properties.setProperty("group.id", "group-" + new Random().nextInt(100000));
			}
			consumer = new KafkaConsumer<String, byte[]>(properties);

			consumer.subscribe(Arrays.asList("eds_services_ccwr_headers","eds_services_ccwr_majorlines","eds_services_ccwr_minorlines"));//, "summary-markers"));

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
					try{
						switch (record.topic()) {
						case "eds_services_ccwr_headers":							
							System.out.println("***Consumed Header MSG :: "+record.value());
							try {
								deserializeMsgValue(record.value(),"header");
							} catch (ClassNotFoundException e) {
								System.out.println("******"+record.value());
								e.printStackTrace();
							}
						case "eds_services_ccwr_majorlines":
							System.out.println("***Consumed Major Line MSG :: "+record.value());
							try {
								deserializeMsgValue(record.value(),"major");
							} catch (ClassNotFoundException e) {
								System.out.println("******"+record.value());
								e.printStackTrace();
							}
						case "eds_services_ccwr_minorlines":
							System.out.println("***Consumed Minor MSG :: "+record.value());	
							try {
								deserializeMsgValue(record.value(),"minor");
							} catch (ClassNotFoundException e) {
								System.out.println("******"+record.value());
								e.printStackTrace();
							}
						case "topic2":
							break;
						default:
							throw new IllegalStateException("Shouldn't be possible to get message on topic " + record.topic());
						}
					}		catch(ClassCastException e){
						System.out.println(record.value());
						continue;
					}
				}
		}

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
			System.out.println("**** "+msg+" :: "+(String)ois.readObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
