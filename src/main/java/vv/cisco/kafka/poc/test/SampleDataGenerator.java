/**
 * 
 */
package vv.cisco.kafka.poc.test;

import vv.cisco.kafka.poc.kafka.utils.JSONUtils;

/**
 * @author vepaleti
 *
 */
public class SampleDataGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		generateQuoteHeaders();
		generateQuoteMajors();
		generateQuoteMinors();
	}
	
	public static void generateQuoteHeaders( ) {
    	for(int i=1; i<2000;i++){
			String quoteHeaderValue = "{\"quoteID\":\""+i+"\","+
					 "\"custAccountId\":\"1111\","+
					 "\"quoteName\":\"E2E Automation\","+
					 "\"takeOverFlag\":\"true\","+
					 "\"federalFlag\":\"true\","+
					 "\"nonStandardQuoteFlag\":\"true\","+
					 "\"totalQuotePriceUsd\":\"10239.2\","+
					 "\"totalQuotePrice\":\"10239.2\","+
					 "\"quoteListPrice\":\"null\","+
					 "\"quoteNetPrice\":\"10239.2\","+
					 "\"lastUpdateDate\":\"2016-12-26 07:10:52.0\","+
					 "\"creationDate\":\"2016-12-21 01:10:52.0\","+
					 "\"createdBy\":\"techdata\","+
					 "\"currencyCode\":\"USD\","+
					 "\"distiId\":\"2013\","+
					 "\"resellerBillToId\":\"401268731\","+
					 "\"quoteStatusId\":\"10005\"}";			
			System.out.println("Sending Header :: "+quoteHeaderValue);
			JSONUtils.processConsumedJSON(quoteHeaderValue);
		}		
	}
	
	public static void generateQuoteMajors(){
	    	for(int i=1; i<2000;i++){
	    		if(i%3==0)
	    			continue;
	    		StringBuilder quoteMajors = new StringBuilder("[ ");
	    		for(int j=1; j<=6;j++){
	    			String quoteMajorValue = "{\"quoteID\":\""+i+"\","+
	    					"\"quoteMajorLineId\":\""+(10000+i+j)+"\","+
	    					"\"targetContractNumber\":\"NEW:New\","+
	    					"\"lineQuotePrice\":\"324.5\","+
	    					"\"lineListPrice\":\"324.5\","+
	    					"\"inventoryItemId\":\"14992256\","+
	    					"\"startDateActive\":\"2016-12-23 05:10:52.0\","+
	    					"\"endDateActive\":\"2016-12-22 05:10:52.0\","+
	    					"\"lastUpdateDate\":\"2016-12-27 05:10:52.0\","+
	    					"\"q2oLineId\":\"null\","+
	    					"\"relatedItemId\":\"4240530\","+
	    					"\"quantity\":\"1\","+
	    					"\"revenueSourceCode\":\"Q\","+
	    					"\"svcCode\":\"Q\","+
	    					"\"instanceId\":\"1459361213\","+
	    					"\"serialNumber\":\"SSI17280E87\","+
	    					"\"coTerm\":\"Yes\","+
	    					"\"cleId\":\"null\","+
	    					"\"renewCleId\":\"-1\","+
	    					"\"statusId\":\"null\"}";
	    			quoteMajors.append(quoteMajorValue);				
	    			if(j!=6)
	    				quoteMajors.append(",");
	    		}
	    		quoteMajors.append("]");	    				
	    		System.out.println(" Generate Quote major "+quoteMajors);
	    		JSONUtils.processConsumedJSON(quoteMajors.toString());

	    	}
	}
	
	public static void generateQuoteMinors() {
		for (int i = 1; i < 2000; i++) {
			StringBuilder quoteMinors = new StringBuilder("{ ");			
			for (int j = 1; j <=6; j++) {
				quoteMinors.append("\""+(10000+i+j)+"\" : [");
				for (int k = 1; k <=3 ; k++) {					
					String quoteMajorValue = "{\"quoteId\":\""+i+"\","+
							"\"quoteMinorLineId\":\""+(30000+i+j+k)+"\","+
							"\"quoteMajorLineId\":\""+(10000+i+j)+"\","+
							"\"instanceId\":\"1787825650\","+
							"\"relatedItemId\":\"4240491\","+
							"\"lineStatusId\":\"10005\","+
							"\"startDateActive\":\"2016-12-23 05:10:52.0\","+
							"\"endDateActive\":\"2016-12-22 05:10:52.0\","+
							"\"lastUpdateDate\":\"2016-12-26 05:10:52.0\","+
							"\"lineListPrice\":\"0.0\","+
							"\"lineQuotePrice\":\"0.0\","+
							"\"lineAdjustedAmount\":\"0.01\","+
							"\"extendedPrice\":\"0.0\","+
							"\"revenueSourceCode\":\"R\","+
							"\"cleId\":\"null\","+
							"\"renewCleId\":\"-1\","+
							"\"quantity\":\"1\"}";
					quoteMinors.append(quoteMajorValue);
					if(k!=3)
						quoteMinors.append(",");
				}
				quoteMinors.append("]");
				if(j!=6)
					quoteMinors.append(",");
				else
					quoteMinors.append("}");
			}
			System.out.println("Generate Quote Minor  "+quoteMinors);
			JSONUtils.processConsumedJSON(quoteMinors.toString());
		}

	}

}
