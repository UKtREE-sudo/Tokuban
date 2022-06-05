package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * t_address用のentity
 * @author 大野
 * @version 0.1.1
 */
@Table(name = "t_address")
@Entity
public class TAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private String zipcode;
	private String addressCore;
	private String addressNumber;
	private String addressBuilding;
	private String toName;

	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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
