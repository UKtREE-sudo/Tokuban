package jp.co.bungeejump.tokuban.entity.virtual;

/**
 * 購入履歴表示用
 * @author 小河原
 * @version 0.1.0
 */

public class PurchaseHistoryDisplay {

	private String date;

	private String time;

	//ここからVMerch
	private Integer merchId;

	private String merchName;

	private String makerName;

	private String prefectureName;

	private Integer includingTax;
	//VMerchここまで

	private Integer purchasedQuantity;


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Integer getMerchId() {
		return merchId;
	}


	public void setMerchId(Integer merchId) {
		this.merchId = merchId;
	}


	public String getMerchName() {
		return merchName;
	}


	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}


	public String getMakerName() {
		return makerName;
	}


	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}


	public String getPrefectureName() {
		return prefectureName;
	}


	public void setPrefectureName(String prefectureName) {
		this.prefectureName = prefectureName;
	}


	public Integer getIncludingTax() {
		return includingTax;
	}


	public void setIncludingTax(Integer includingTax) {
		this.includingTax = includingTax;
	}


	public Integer getPurchasedQuantity() {
		return purchasedQuantity;
	}


	public void setPurchasedQuantity(Integer purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	// tostring
	@Override
	public String toString() {
		return "PurchaseHistoryDisplay [date=" + date + ", merchId" + merchId + ", merchName=" + merchName + ", makerName="
				+ makerName + ", prefectureName=" + prefectureName + ", basePrice" + includingTax + ", purchasedQuantity="
				+ purchasedQuantity + "]";
	}

}
