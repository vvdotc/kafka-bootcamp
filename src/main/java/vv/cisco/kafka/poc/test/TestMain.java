package vv.cisco.kafka.poc.test;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestMain {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		

		StringBuilder quoteMinors = new StringBuilder("[ ");
		String quoteMinorKey =" quoteMinor-"+1;
		for (int j = 1; j <=6; j++) {
			for (int k = 1; k <=3 ; k++) {					
				String quoteMajorValue = "{\"quoteId\":\""+1+"\","+
						"\"quoteMinorLineId\":\""+(j+k)+"\","+
    					"\"quoteMajorLineId\":\""+j+"\","+
    					"\"instanceId\":\"1787825650\","+
    					"\"relatedItemId\":\"4240491\","+
    					"\"lineStatusId\":\"10005\","+
    					"\"startDateActive\":\"2016-12-23 05:10:52.0\","+
    					"\"endDateActive\":\"2016-12-22 05:10:52.0\","+
    					"\"lastUpdateDate\":\"2016-12-24 05:10:52.0\","+
    					"\"lineListPrice\":\"0.0\","+
    					"\"lineQuotePrice\":\"0.0\","+
    					"\"lineAdjustedAmount\":\"0.0\","+
    					"\"extendedPrice\":\"0.0\","+
    					"\"revenueSourceCode\":\"R\","+
    					"\"cleId\":\"null\","+
    					"\"renewCleId\":\"-1\","+
    					"\"quantity\":\"1\"}";
				quoteMinors.append(quoteMajorValue);
				if(k!=3)
				quoteMinors.append(",");
			}
			if(j!=6)
				quoteMinors.append(",");
			else
				quoteMinors.append("]");
		}			
		System.out.println("Sent Key N Value : " +" "+quoteMinors);
		
		JsonNode testJSON = new ObjectMapper().readTree(quoteMinors.toString());
		System.out.println("ToString :: "+testJSON.toString());
				final JsonNode arrNode = new ObjectMapper().readTree(testJSON.toString());
		
	
	
		String dateStr = "Wed Dec 21 01: 10: 52 PST 2016";
		Date date = null;
		Date myStr = null;
		String OLD_FORMAT = "E MMM dd HH:mm:ss Z yyyy";
		String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
		String newDateString = null;
		try {
			DateFormat qformatter = new SimpleDateFormat(OLD_FORMAT);
			Date d = qformatter.parse(dateStr);
			((SimpleDateFormat) qformatter).applyPattern(NEW_FORMAT);
			newDateString = qformatter.format(d);
			System.out.println(newDateString);

			Timestamp ts = Timestamp.valueOf(newDateString);
			System.out.println("TS ::"+ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

/*
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR) +" : ";
		System.out.println("formatedDate : " + formatedDate);    */

	}

}
