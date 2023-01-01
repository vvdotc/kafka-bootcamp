/**
 * 
 */
package vv.cisco.kafka.poc.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author vepaleti
 *
 */
public class MainOperations {

	public static void main(String[] args) {
		Date dt = new Date();
				
		//SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()) ;//SimpleDateFormat("ddMMyyyy");
		String dForm = new  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date());
		System.out.println("dFormat"+ dForm);

	}

}
