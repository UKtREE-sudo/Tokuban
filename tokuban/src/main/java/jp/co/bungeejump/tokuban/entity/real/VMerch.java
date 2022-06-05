package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * v_merch用のentity
 * @author 周東/ 小河原
 * @version 0.0.2
 */
@Entity
@Table(name = "v_merch")
public class VMerch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "merch_id")
	private Integer merchId;

	@Column(name = "merch_name")
	private String merchName;

	@Column(name = "maker_name")
	private String makerName;

	@Column(name = "prefecture_name")
	private String prefectureName;

	@Column(name = "base_price")
	private Integer basePrice;

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

	public Integer getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}

}
