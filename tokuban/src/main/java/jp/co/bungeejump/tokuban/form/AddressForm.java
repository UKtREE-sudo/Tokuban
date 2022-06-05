package jp.co.bungeejump.tokuban.form;

/**
 * 配送先情報のフォーム
 * @author 大野
 * @version 0.0.0
 *
 */
public class AddressForm {

	//↓郵便番号
	private String zipcode;
	//↓住所1(都道府県とか)
	private String addressCore;
	//↓住所2(丁目とか)
	private String addressNumber;
	//↓住所3(ビルとか)
	private String addressBuilding;
	//↓氏名
	private String toName;

	public AddressForm(){

	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddressCore() {
		return addressCore;
	}

	public void setAddressCore(String addressCore) {
		this.addressCore = addressCore;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressBuilding() {
		return addressBuilding;
	}

	public void setAddressBuilding(String addressBuilding) {
		this.addressBuilding = addressBuilding;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

}
