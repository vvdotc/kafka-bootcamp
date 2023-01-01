package vv.cisco.kafka.poc.kafka.model;

/**
 * @author vepaleti
 *
 */
public class QuoteMajor {
	
	private String quoteId; 
	private String quoteMajorLineId ;
	private String targetContractNumber; 
	private String lineQuotePrice;
	private String lineListPrice ;
	private String inventoryItemId; 
	private String startDateActive;
	private String endDateActive;
	private String lastUpdateDate; 
	private String q2oLineId ;
	private String relatedItemId; 
	private String quantity ;
	private String revenueSourceCode; 
	private String svcCode ;
	private String instanceId; 
	private String serialNumber; 
	private String coTerm ;
	private String cleId; 
	private String renewCleId ;
	private String statusId;
	
	/**
	 * 
	 */
	public QuoteMajor( String quoteId, 
			String quoteMajorLineId ,
			String targetContractNumber, 
			String lineQuotePrice,
			String lineListPrice,
			String inventoryItemId, 
			String startDateActive,
			String endDateActive,
			String lastUpdateDate, 
			String q2oLineId,
			String relatedItemId, 
			String quantity,
			String revenueSourceCode, 
			String svcCode,
			String instanceId, 
			String serialNumber, 
			String coTerm,
			String cleId,
			String renewCleId, 
			String statusId) {
		 this.quoteId = quoteId;
		 this.quoteMajorLineId = quoteMajorLineId;
		 this.targetContractNumber = targetContractNumber; 
		 this.lineQuotePrice = lineQuotePrice;
		 this.lineListPrice = lineListPrice;
		 this.inventoryItemId = inventoryItemId;
		 this.startDateActive = startDateActive;
		 this.endDateActive = endDateActive;
		 this.lastUpdateDate =  lastUpdateDate;
		 this.q2oLineId = q2oLineId;
		 this.relatedItemId = relatedItemId;
		 this.quantity = quantity;
		 this.revenueSourceCode= revenueSourceCode;
		 this.svcCode = svcCode;
		 this.instanceId = instanceId;
		 this.serialNumber =serialNumber;
		 this.coTerm = coTerm;
		 this.cleId =cleId;
		 this.renewCleId = renewCleId;
		 this.statusId = statusId;
	}
	
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getQuoteMajorLineId() {
		return quoteMajorLineId;
	}
	public void setQuoteMajorLineId(String quoteMajorLineId) {
		this.quoteMajorLineId = quoteMajorLineId;
	}
	public String getTargetContractNumber() {
		return targetContractNumber;
	}
	public void setTargetContractNumber(String targetContractNumber) {
		this.targetContractNumber = targetContractNumber;
	}
	public String getLineQuotePrice() {
		return lineQuotePrice;
	}
	public void setLineQuotePrice(String lineQuotePrice) {
		this.lineQuotePrice = lineQuotePrice;
	}
	public String getLineListPrice() {
		return lineListPrice;
	}
	public void setLineListPrice(String lineListPrice) {
		this.lineListPrice = lineListPrice;
	}
	public String getInventoryItemId() {
		return inventoryItemId;
	}
	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}
	public String getStartDateActive() {
		return startDateActive;
	}
	public void setStartDateActive(String startDateActive) {
		this.startDateActive = startDateActive;
	}
	public String getEndDateActive() {
		return endDateActive;
	}
	public void setEndDateActive(String endDateActive) {
		this.endDateActive = endDateActive;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getQ2oLineId() {
		return q2oLineId;
	}
	public void setQ2oLineId(String q2oLineId) {
		this.q2oLineId = q2oLineId;
	}
	public String getRelatedItemId() {
		return relatedItemId;
	}
	public void setRelatedItemId(String relatedItemId) {
		this.relatedItemId = relatedItemId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRevenueSourceCode() {
		return revenueSourceCode;
	}
	public void setRevenueSourceCode(String revenueSourceCode) {
		this.revenueSourceCode = revenueSourceCode;
	}
	public String getSvcCode() {
		return svcCode;
	}
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getCoTerm() {
		return coTerm;
	}
	public void setCoTerm(String coTerm) {
		this.coTerm = coTerm;
	}
	public String getCleId() {
		return cleId;
	}
	public void setCleId(String cleId) {
		this.cleId = cleId;
	}
	public String getRenewCleId() {
		return renewCleId;
	}
	public void setRenewCleId(String renewCleId) {
		this.renewCleId = renewCleId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	

}
