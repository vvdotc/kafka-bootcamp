/**
 * 
 */
package vv.cisco.kafka.poc.kafka.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import vv.cisco.kafka.poc.kafka.model.QuoteHeader;
import vv.cisco.kafka.poc.kafka.model.QuoteMajor;
import vv.cisco.kafka.poc.kafka.model.QuoteMinor;

/**
 * @author vepaleti
 *
 */
public class JSONUtils {
	private static ObjectMapper mapper = new ObjectMapper(); 

	/**
	 * @param msg
	 */
	
	public static void processConsumedJSON(byte[] msg) {
		String strMsg = null;
		try {
			strMsg = deserializeByteMsg(msg);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		processConsumedJSON(strMsg);
	}
	public static void processConsumedJSON(String msg) {
		System.out.println("*** JSON Msg after deserialization :: "+msg);
		JsonNode jsonMsg;
		try {
			jsonMsg = mapper.readTree(msg);
			if(jsonMsg.isArray()){    
				Iterator<JsonNode> jsonIterator = jsonMsg.elements();
				while (jsonIterator.hasNext()) {
					JsonNode qMajorJSON = jsonIterator.next();    
					//saveMajortoCSV(qMajorJSON);
				}
			}else if(jsonMsg.get("quoteMajorLineId") != null){            			
				//saveMajortoCSV(jsonMsg);
			}else if(jsonMsg.get("quoteID")!=null){
				//saveHeaderToCSV(jsonMsg);
			}else{
				System.out.println("Received JSON is Minor Quote");
				Iterator<String> keys = jsonMsg.fieldNames();				
				while(keys.hasNext()){
					String key = keys.next();
					final JsonNode arrNode = new ObjectMapper().readTree(jsonMsg.toString()).get(key);
					if(arrNode.isArray()){
						Iterator<JsonNode> minorItr= arrNode.elements();
						//saveQouteMinorToCSV(minorItr.next());
					}else{
						//saveQouteMinorToCSV(arrNode);
					}
				}
			}	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * @param msg
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private static String deserializeByteMsg(byte[] msg) throws ClassNotFoundException, IOException  {
		ObjectInputStream o = null;
		String strMsg = null;
		ByteArrayInputStream b = new ByteArrayInputStream(msg);
		o = new ObjectInputStream(b);
		strMsg =  (String)o.readObject();
		return strMsg;
	}
	
	/**
	 * @return
	 */
	
	public static String deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		return (String)o.readObject();

	}

	/**
	 *
	 * @param msg
	 */
	private static void saveQouteMinorToCSV(JsonNode msg) {
		String quoteMinorLineId = msg.get("quoteMinorLineId").asText();
		String quoteMajorLineId = msg.get("quoteMajorLineId").asText();
		String quoteId = msg.get("quoteId").asText();		
		String instanceId =  msg.get("instanceId").asText();
		String relatedItemId =  msg.get("relatedItemId").asText();
		String lineStatusId = msg.get("lineStatusId").asText();
		String startDateActive = msg.get("startDateActive").asText();
		String endDateActive = msg.get("endDateActive").asText();
		String lastUpdateDate = msg.get("lastUpdateDate").asText();
		String lineListPrice = msg.get("lineListPrice").asText();
		String lineQuotePrice = msg.get("lineQuotePrice").asText();
		String lineAdjustedAmount = msg.get("lineAdjustedAmount").asText();
		String extendedPrice = msg.get("extendedPrice").asText();
		String revenueSourceCode = msg.get("revenueSourceCode").asText();
		String cleId = msg.get("cleId").asText();
		String renewCleId = msg.get("renewCleId").asText();
		String quantity = msg.get("quantity").asText();
		QuoteMinor qMinor = new QuoteMinor(quoteId, quoteMinorLineId, quoteMajorLineId, instanceId, relatedItemId, lineStatusId,
				startDateActive, endDateActive, lastUpdateDate, lineListPrice, lineQuotePrice, lineAdjustedAmount, 
				extendedPrice, revenueSourceCode, cleId, renewCleId, quantity);
		try {
			CSVUtils.saveQuoteMinor(qMinor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param msg
	 */
	private static void saveMajortoCSV(JsonNode msg) {
		String quoteMajorLineId = msg.get("quoteMajorLineId").asText();
		String quoteId = msg.get("quoteID").asText();		
		String targetContractNumber = msg.get("targetContractNumber").asText();
		String lineQuotePrice = msg.get("lineQuotePrice").asText();
		String lineListPrice = msg.get("lineListPrice").asText();
		String inventoryItemId = msg.get("inventoryItemId").asText();
		String startDateActive = msg.get("startDateActive").asText();
		String endDateActive = msg.get("endDateActive").asText();
		String q2oLineId = msg.get("q2oLineId").asText();
		String relatedItemId = msg.get("relatedItemId").asText();
		String lastUpdateDate = msg.get("lastUpdateDate").asText();
		String quantity = msg.get("quantity").asText();
		String revenueSourceCode = msg.get("revenueSourceCode").asText();
		String svcCode = msg.get("svcCode").asText();
		String instanceId = msg.get("instanceId").asText();
		String serialNumber = msg.get("serialNumber").asText();
		String coTerm = msg.get("coTerm").asText();
		String cleId = msg.get("cleId").asText();
		String renewCleId = msg.get("renewCleId").asText();
		String statusId = msg.get("renewCleId").asText();

		QuoteMajor qMajor = new QuoteMajor( quoteId,
				quoteMajorLineId ,
				targetContractNumber, 
				lineQuotePrice,
				lineListPrice,
				inventoryItemId, 
				startDateActive,
				endDateActive,
				lastUpdateDate, 
				q2oLineId,
				relatedItemId, 
				quantity,
				revenueSourceCode, 
				svcCode,
				instanceId, 
				serialNumber, 
				coTerm,
				cleId,
				renewCleId, 
				statusId);
		/*try {
			CSVUtils.saveQuoteMajor(qMajor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * @param msg
	 * @return
	 */
	private static void saveHeaderToCSV(JsonNode msg) {
		String quoteId = msg.get("quoteID").asText();
		String custAccountId = msg.get("custAccountId").asText();
		String  quoteName = msg.get("quoteName").asText();
		String takeOverFlag = msg.get("takeOverFlag").asText();
		String federalFlag = msg.get("federalFlag").asText();
		String nonStandardQuoteFlag = msg.get("nonStandardQuoteFlag").asText();
		String totalQuotePriceUsd = msg.get("totalQuotePriceUsd").asText();
		String totalQuotePrice = msg.get("totalQuotePrice").asText();
		String quoteListPrice = msg.get("quoteListPrice").asText();
		String quoteNetPrice = msg.get("quoteNetPrice").asText();
		String lastUpdateDate = msg.get("lastUpdateDate").asText();
		String creationDate = msg.get("creationDate").asText();
		String createdBy = msg.get("createdBy").asText();
		String currencyCode = msg.get("currencyCode").asText();
		String distiId = msg.get("distiId").asText();
		String resellerBillToId = msg.get("resellerBillToId").asText();
		String quoteStatusId = msg.get("quoteStatusId").asText();
		QuoteHeader qHeader = new QuoteHeader(quoteId,
				custAccountId,
				quoteName,
				takeOverFlag,
				federalFlag,
				nonStandardQuoteFlag,
				totalQuotePriceUsd,
				totalQuotePrice,
				quoteListPrice,
				quoteNetPrice,
				lastUpdateDate,
				creationDate,
				createdBy,
				currencyCode,
				distiId,
				resellerBillToId,
				quoteStatusId
				);
		try {
			CSVUtils.saveQuoteHeader(qHeader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
