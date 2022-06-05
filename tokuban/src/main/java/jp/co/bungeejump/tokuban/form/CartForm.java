package jp.co.bungeejump.tokuban.form;
/**
 * カート情報を送受信する
 * @author 末永
 * @version 0.2.0
 */
public class CartForm {

	//フロントからバックに送るときにも入る情報
	private Integer merchId;
	private Integer quantity;

	//バックからフロントに送るときだけ入る情報
	private String merchName;
	private String prefectureName;
	private String makerName;
	private Integer includingTax;

	public Integer getMerchId() {
		return merchId;
	}
	public void setMerchId(Integer merchId) {
		this.merchId = merchId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getPrefectureName() {
		return prefectureName;
	}
	public void setPrefectureName(String prefectureName) {
		this.prefectureName = prefectureName;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public Integer getIncludingTax() {
		return includingTax;
	}
	public void setIncludingTax(Integer includingTax) {
		this.includingTax = includingTax;
	}
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
}
