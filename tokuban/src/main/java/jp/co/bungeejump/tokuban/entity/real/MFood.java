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

@Entity
@Table(name = "m_food")
public class MFood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "merch_id")
	private Integer merchId;
	
	@Column(name = "merch_name")
	private String merchName;
	
	@Column(name = "base_price")
	private Integer basePrice;
	
	@Column(name = "prefecture_id")
	private Integer prefectureId;
	
	@Column(name = "maker_id")
	private Integer makerId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "merch_detail")
	private String merchDetail;
	
	@Column(name = "merch_content")
	private String merchContent;
	
	@Column(name = "best_before")
	private String bestBefore;
	
	@Column(name = "store_method")
	private String storeMethod;
	
	@Column(name = "made_from")
	private String madeFrom;
	
	@Column(name = "allergen")
	private String allergen;
	
	@Column(name = "registered_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registeredTimestamp;

	// getter/setter
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

	public Integer getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}

	public Integer getPrefectureId() {
		return prefectureId;
	}

	public void setPrefectureId(Integer prefectureId) {
		this.prefectureId = prefectureId;
	}

	public Integer getMakerId() {
		return makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getMerchDetail() {
		return merchDetail;
	}

	public void setMerchDetail(String merchDetail) {
		this.merchDetail = merchDetail;
	}

	public String getMerchContent() {
		return merchContent;
	}

	public void setMerchContent(String merchContent) {
		this.merchContent = merchContent;
	}

	public String getBestBefore() {
		return bestBefore;
	}

	public void setBestBefore(String bestBefore) {
		this.bestBefore = bestBefore;
	}

	public String getStoreMethod() {
		return storeMethod;
	}

	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}

	public String getMadeFrom() {
		return madeFrom;
	}

	public void setMadeFrom(String madeFrom) {
		this.madeFrom = madeFrom;
	}

	public String getAllergen() {
		return allergen;
	}

	public void setAllergen(String allergen) {
		this.allergen = allergen;
	}

	public Date getRegisteredTimestamp() {
		return registeredTimestamp;
	}

	public void setRegisteredTimestamp(Date registeredTimestamp) {
		this.registeredTimestamp = registeredTimestamp;
	}
	
}
