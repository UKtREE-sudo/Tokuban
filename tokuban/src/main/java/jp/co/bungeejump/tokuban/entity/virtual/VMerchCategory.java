package jp.co.bungeejump.tokuban.entity.virtual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * v_merch_category用のentity
 * @author 高根
 * @version 0.0.1
 * MerchDateliDaoで使います
 */
@Entity
@Table(name = "v_merch_category")
public class VMerchCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "merch_id")
	private Integer merchId;

	@Column(name = "merch_name")
	private String merchName;

	@Column(name = "category_name")
	private String categoryName;

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
