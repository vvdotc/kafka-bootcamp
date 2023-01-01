/**
 * 
 */
package vv.cisco.kafka.poc.kafka.model;

/**
 * @author vepaleti
 *
 */
public class QuoteMinor {
	private String  quoteId;
	private String  quoteMinorLineId;
	private String  quoteMajorLineId;
	private String  instanceId;
	private String  relatedItemId;
	private String  lineStatusId;
	private String  startDateActive;
	private String  endDateActive;
	private String  lastUpdateDate;
	private String  lineListPrice;
	private String  lineQuotePrice;
	private String  lineAdjustedAmount;
	private String  extendedPrice;
	private String  revenueSourceCode;
	private String  cleId;
	private String  renewCleId;
	private String  quantity;
	/**
	 * 
	 */
	public QuoteMinor(
			String quoteId,
			String quoteMinorLineId,
			String quoteMajorLineId,
			String instanceId,
			String relatedItemId,
			String lineStatusId,
			String startDateActive,
			String endDateActive,
			String lastUpdateDate,
			String lineListPrice,
			String lineQuotePrice,
			String lineAdjustedAmount,
			String  extendedPrice,
			String revenueSourceCode,
			String cleId,
			String renewCleId,
			String quantity) {
		this.quoteId = quoteId;
		this.quoteMinorLineId = quoteMinorLineId;
		this.quoteMajorLineId = quoteMajorLineId;
		this.instanceId = instanceId;
		this.relatedItemId = relatedItemId;
		this.lineStatusId = lineStatusId;
		this.startDateActive = startDateActive;
		this.endDateActive = endDateActive;
		this.lastUpdateDate = lastUpdateDate;
		this.lineListPrice = lineListPrice;
		this.lineQuotePrice = lineQuotePrice;
		this.lineAdjustedAmount = lineAdjustedAmount;
		this.extendedPrice = extendedPrice;
		this.revenueSourceCode = revenueSourceCode;
		this.cleId = cleId;
		this.renewCleId = renewCleId;
		this.quantity = quantity;
		
	}
	
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getQuoteMinorLineId() {
		return quoteMinorLineId;
	}
	public void setQuoteMinorLineId(String quoteMinorLineId) {
		this.quoteMinorLineId = quoteMinorLineId;
	}
	public String getQuoteMajorLineId() {
		return quoteMajorLineId;
	}
	public void setQuoteMajorLineId(String quoteMajorLineId) {
		this.quoteMajorLineId = quoteMajorLineId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getRelatedItemId() {
		return relatedItemId;
	}
	public void setRelatedItemId(String relatedItemId) {
		this.relatedItemId = relatedItemId;
	}
	public String getLineStatusId() {
		return lineStatusId;
	}
	public void setLineStatusId(String lineStatusId) {
		this.lineStatusId = lineStatusId;
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
	public String getLineListPrice() {
		return lineListPrice;
	}
	public void setLineListPrice(String lineListPrice) {
		this.lineListPrice = lineListPrice;
	}
	public String getLineQuotePrice() {
		return lineQuotePrice;
	}
	public void setLineQuotePrice(String lineQuotePrice) {
		this.lineQuotePrice = lineQuotePrice;
	}
	public String getLineAdjustedAmount() {
		return lineAdjustedAmount;
	}
	public void setLineAdjustedAmount(String lineAdjustedAmount) {
		this.lineAdjustedAmount = lineAdjustedAmount;
	}
	public String getExtendedPrice() {
		return extendedPrice;
	}
	public void setExtendedPrice(String extendedPrice) {
		this.extendedPrice = extendedPrice;
	}
	public String getRevenueSourceCode() {
		return revenueSourceCode;
	}
	public void setRevenueSourceCode(String revenueSourceCode) {
		this.revenueSourceCode = revenueSourceCode;
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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


}
