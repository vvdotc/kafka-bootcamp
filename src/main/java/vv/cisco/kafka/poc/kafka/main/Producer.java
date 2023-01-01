package vv.cisco.kafka.poc.kafka.main;

import com.google.common.io.Resources;

import vv.cisco.kafka.poc.kafka.utils.PublishJSONMsgs;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * This producer will send a bunch of messages to topic.
 * 
 * Run the class using java -cp /path/xyz.jar vv.poc.kafka.main.Producer
 */
public class Producer {
	public static void main(String[] args) throws IOException {
		
		// set up the producer
		KafkaProducer<String, String> producer = null;
		String propsFile = "cisco/producer.props";
		if(args.length == 0){
			return;
		}else if(args[0].equals("STG")){
			propsFile = "cisco/producer-stg.props";
		}
		try (InputStream props = Resources.getResource(propsFile).openStream()) {
			Properties properties = new Properties();
			properties.load(props);
			producer = new KafkaProducer<String, String>(properties);
		}
		if(args.length ==1){			
			PublishJSONMsgs.publishQuoteHeaders(producer,"eds_services_ccwr_headers");
		}else if(args[1].equals("Major")){			
			PublishJSONMsgs.publishQuoteMajors(producer,"eds_services_ccwr_majorlines");
		}else if(args[1].equals("Minor")){
			PublishJSONMsgs.publishQuoteMinors(producer,"eds_services_ccwr_minorlines");
		}		
		producer.close();
	}

}
