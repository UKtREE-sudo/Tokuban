package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import jp.co.bungeejump.tokuban.entity.mpk.TCartPK;

/**
 * t_cartテーブル用のentity
 * @author 末永
 * @version 0.0.1
 *
 */
@Entity
@Table(name="t_cart")
@IdClass(TCartPK.class)
public class TCart {
	@Id
	@Column (name="user_id")
	private Integer userId;
	
	@Id
	@Column (name="merch_id")
	private Integer merchId;
	
	@Column (name="quantity")
	private Integer quantity;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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

}
