package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * t_purchase用のentity
 * @author 小河原
 * @version 0.0.1
 */
@Entity
@Table(name = "t_purchase")
public class TPurchase {

	// field ↓
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private Integer purchaseId; // 購入履歴ID

	@Column(name = "settlement_id")
	private Integer settlementId; // 決済ID

	@Column(name = "merch_id")
	private Integer merchId; // 商品ID

	@Column(columnDefinition = "INT CHECK (1<= quantity <= 10)")
	private Integer quantity; // 個数

	// 未評価の場合0
	@Column(columnDefinition = "INT CHECK (CHECK(0 <= r_star <= 5)")
	private Integer rStar; // おすすめ度
	// field ↑

	// setter/getter ↓
	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
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

	public Integer getrStar() {
		return rStar;
	}

	public void setrStar(Integer rStar) {
		this.rStar = rStar;
	}
	// setter/getter ↑

	// toString ↓
	@Override
	public String toString() {
		return "TPurchase [purchaseId=" + purchaseId + ", settlementId=" + settlementId + ", merchId=" + merchId
				+ ", quantity=" + quantity + ", rStar=" + rStar + "]";
	}
	// toString ↑

}
