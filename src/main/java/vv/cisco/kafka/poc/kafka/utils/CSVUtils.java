package vv.cisco.kafka.poc.kafka.utils;

import java.io.FileWriter;
import java.io.IOException;

import vv.cisco.kafka.poc.kafka.model.QuoteHeader;
import vv.cisco.kafka.poc.kafka.model.QuoteMajor;
import vv.cisco.kafka.poc.kafka.model.QuoteMinor;

public class CSVUtils {
	public static void saveQuoteHeader(QuoteHeader qhJSONObj) throws IOException{
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:\\SampleData\\QuoteHeader.txt",true);
		} catch (IOException e) {
			fw = new FileWriter("C:\\SampleData\\QuoteHeader.txt");
		}
		fw.append(qhJSONObj.getQuoteId()+",");
		fw.append(qhJSONObj.getCustAccountId()+",");
		fw.append(qhJSONObj.getQuoteName()+",");
		fw.append(qhJSONObj.getTakeOverFlag()+",");
		fw.append(qhJSONObj.getFederalFlag()+",");
		fw.append(qhJSONObj.getNonStandardQuoteFlag()+",");
		fw.append(qhJSONObj.getTotalQuotePriceUsd()+",");
		fw.append(qhJSONObj.getTotalQuotePrice()+",");
		fw.append(qhJSONObj.getQuoteListPrice()+",");
		fw.append(qhJSONObj.getQuoteNetPrice()+",");
		fw.append(qhJSONObj.getLastUpdateDate()+",");
		fw.append(qhJSONObj.getCreationDate()+",");
		fw.append(qhJSONObj.getCurrencyCode()+",");
		fw.append(qhJSONObj.getCreatedBy()+",");
		fw.append(qhJSONObj.getDistiId()+",");
		fw.append(qhJSONObj.getResellerBillToId()+",");
		fw.append(qhJSONObj.getQuoteStatusId()+",");
		fw.append(null+"\n");

		fw.flush();
		fw.close();
	}

	/**
	 * @param qMajor
	 * @throws IOException 
	 */
	public static void saveQuoteMajor(QuoteMajor qMajor) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:\\SampleData\\QuoteLine.txt",true);
		} catch (IOException e) {
			fw = new FileWriter("C:\\SampleData\\QuoteLine.txt");
		} 		

		fw.append(qMajor.getQuoteId()+",");
		fw.append(qMajor.getQuoteMajorLineId()+",");
		fw.append(qMajor.getTargetContractNumber()+",");
		fw.append(qMajor.getLineQuotePrice()+",");
		fw.append(qMajor.getLineListPrice()+",");
		fw.append(qMajor.getInventoryItemId()+",");
		fw.append(qMajor.getStartDateActive()+",");
		fw.append(qMajor.getEndDateActive()+",");
		fw.append(qMajor.getLastUpdateDate()+",");
		fw.append(qMajor.getQ2oLineId()+",");
		fw.append(qMajor.getRelatedItemId()+",");
		fw.append(qMajor.getQuantity()+",");
		fw.append(qMajor.getRevenueSourceCode()+",");
		fw.append(qMajor.getSvcCode()+",");
		fw.append(qMajor.getInstanceId()+",");
		fw.append(qMajor.getSerialNumber()+",");
		fw.append(qMajor.getCoTerm()+",");
		fw.append(qMajor.getCleId()+",");
		fw.append(qMajor.getRenewCleId()+",");
		fw.append(qMajor.getStatusId()+"\n");

		fw.flush();
		fw.close();

	}

	/**
	 * @param qMinor
	 * @throws IOException 
	 */
	public static void saveQuoteMinor(QuoteMinor qMinor) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:\\SampleData\\QuoteMinor.txt",true);
		} catch (IOException e) {
			fw = new FileWriter("C:\\SampleData\\QuoteMinor.txt");
		} 
		fw.append(qMinor.getQuoteId()+",");
		fw.append(qMinor.getQuoteMinorLineId()+",");
		fw.append(qMinor.getQuoteMajorLineId()+",");
		fw.append(qMinor.getInstanceId()+",");
		fw.append(qMinor.getRelatedItemId()+",");
		fw.append(qMinor.getLineStatusId()+",");
		fw.append(qMinor.getStartDateActive()+",");
		fw.append(qMinor.getEndDateActive()+",");
		fw.append(qMinor.getLastUpdateDate()+",");
		fw.append(qMinor.getLineListPrice()+",");
		fw.append(qMinor.getLineQuotePrice()+",");
		fw.append(qMinor.getLineAdjustedAmount()+",");
		fw.append(qMinor.getExtendedPrice()+",");
		fw.append(qMinor.getRevenueSourceCode()+",");
		fw.append(qMinor.getCleId()+",");
		fw.append(qMinor.getRenewCleId()+",");
		fw.append(qMinor.getQuantity()+"\n");
		
		fw.flush();
		fw.close();
		
	}

}
