package jp.co.bungeejump.tokuban.form;

/**
 * SettlementService用のform
 * @author 小河原
 * @version 0.0.1
 */
public class SettlementForm {

	private String date;

	private String merchInformation;

	private int quantity;

	private int sum;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMerchInformation() {
		return merchInformation;
	}

	public void setMerchInformation(String merchInformation) {
		this.merchInformation = merchInformation;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
