package vv.cisco.kafka.poc.kafka.model;

public class QuoteHeader {
	
	private String  quoteId;
	private String custAccountId;
	private String  quoteName;
	private String takeOverFlag;
	private String federalFlag;
	private String nonStandardQuoteFlag;
	private String totalQuotePriceUsd;
	private String totalQuotePrice;
	private String quoteListPrice;
	private String quoteNetPrice;
	private String lastUpdateDate;
	private String creationDate;
	private String createdBy;
	private String currencyCode;
	private String distiId;
	private String resellerBillToId;
	private String quoteStatusId;
	
	/**
	 * 
	 * @param quoteId
	 * @param custAccountId
	 * @param quoteName
	 * @param takeOverFlag
	 * @param federalFlag
	 * @param nonStandardQuoteFlag
	 * @param totalQuotePriceUsd
	 * @param totalQuotePrice
	 * @param quoteListPrice
	 * @param quoteNetPrice
	 * @param lastUpdateDate
	 * @param creationDate
	 * @param createdBy
	 * @param currencyCode
	 * @param distiId
	 * @param resellerBillToId
	 * @param quoteStatusId
	 */
	public QuoteHeader(String quoteId, String custAccountId, String quoteName, String takeOverFlag, 
			String federalFlag, String nonStandardQuoteFlag, String totalQuotePriceUsd, String totalQuotePrice,
			String quoteListPrice, String quoteNetPrice, String lastUpdateDate, String creationDate,
			String createdBy, String currencyCode, String distiId, String resellerBillToId, String quoteStatusId) {
		this.quoteId = quoteId;
		this.custAccountId = custAccountId;
		this.quoteName = quoteName;
		this.takeOverFlag = takeOverFlag;
		this.federalFlag = federalFlag;
		this.nonStandardQuoteFlag = nonStandardQuoteFlag;
		this.totalQuotePriceUsd = totalQuotePriceUsd;
		this.totalQuotePrice = totalQuotePrice;
		this.quoteListPrice = quoteListPrice;
		this.quoteNetPrice = quoteNetPrice;
		this.lastUpdateDate = lastUpdateDate;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.currencyCode = currencyCode;
		this.distiId = distiId;
		this.resellerBillToId = resellerBillToId;
		this.quoteStatusId = quoteStatusId;
	}
	
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getCustAccountId() {
		return custAccountId;
	}
	public void setCustAccountId(String custAccountId) {
		this.custAccountId = custAccountId;
	}	
	public String getQuoteName() {
		return quoteName;
	}
	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}
	public String getTakeOverFlag() {
		return takeOverFlag;
	}
	public void setTakeOverFlag(String takeOverFlag) {
		this.takeOverFlag = takeOverFlag;
	}
	public String getFederalFlag() {
		return federalFlag;
	}
	public void setFederalFlag(String federalFlag) {
		this.federalFlag = federalFlag;
	}
	public String getNonStandardQuoteFlag() {
		return nonStandardQuoteFlag;
	}
	public void setNonStandardQuoteFlag(String nonStandardQuoteFlag) {
		this.nonStandardQuoteFlag = nonStandardQuoteFlag;
	}
	public String getTotalQuotePriceUsd() {
		return totalQuotePriceUsd;
	}
	public void setTotalQuotePriceUsd(String totalQuotePriceUsd) {
		this.totalQuotePriceUsd = totalQuotePriceUsd;
	}
	public String getTotalQuotePrice() {
		return totalQuotePrice;
	}
	public void setTotalQuotePrice(String totalQuotePrice) {
		this.totalQuotePrice = totalQuotePrice;
	}
	public String getQuoteListPrice() {
		return quoteListPrice;
	}
	public void setQuoteListPrice(String quoteListPrice) {
		this.quoteListPrice = quoteListPrice;
	}
	public String getQuoteNetPrice() {
		return quoteNetPrice;
	}
	public void setQuoteNetPrice(String quoteNetPrice) {
		this.quoteNetPrice = quoteNetPrice;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getDistiId() {
		return distiId;
	}
	public void setDistiId(String distiId) {
		this.distiId = distiId;
	}
	public String getResellerBillToId() {
		return resellerBillToId;
	}
	public void setResellerBillToId(String resellerBillToId) {
		this.resellerBillToId = resellerBillToId;
	}
	public String getQuoteStatusId() {
		return quoteStatusId;
	}
	public void setQuoteStatusId(String quoteStatusId) {
		this.quoteStatusId = quoteStatusId;
	}
	
	
	

}
