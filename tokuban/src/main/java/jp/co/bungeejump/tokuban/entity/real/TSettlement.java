package jp.co.bungeejump.tokuban.entity.real;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

/**
 * t_settlement用のentity
 * @author 小河原
 * @version 0.0.1
 */
@Entity
@Table(name = "t_settlement")
public class TSettlement {

	// field ↓
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "settlement_id")
	private Integer settlementId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "address_id")
	private Integer addressId;

	@Column(name = "coupons")
	private Integer coupons;

	@Column(name = "settlement_timestamp")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date settlementTimestamp;
	// field

	// getter/setter ↓
	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getCoupons() {
		return coupons;
	}

	public void setCoupons(Integer coupons) {
		this.coupons = coupons;
	}

	public Date getSettlementTimestamp() {
		return settlementTimestamp;
	}

	public void setSettlementTimestamp(Date settlementTimestamp) {
		this.settlementTimestamp = settlementTimestamp;
	}
	// getter/setter ↑

	// toString ↓
	@Override
	public String toString() {
		return "TSettlement [settlementId=" + settlementId + ", userId=" + userId + ", addressId=" + addressId
				+ ", coupons=" + coupons + ", settlementTimestamp=" + settlementTimestamp + "]";
	}
	// toString ↑

}






















