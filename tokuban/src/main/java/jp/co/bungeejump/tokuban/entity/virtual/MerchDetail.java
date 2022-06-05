package jp.co.bungeejump.tokuban.entity.virtual;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 商品詳細を取得するやつ
 * @author 高根
 *
 */
@Entity
public class MerchDetail {
	//商品詳細ページで「同じ地方のおすすめ」を出すことを考えるとInteger型のregionIdフィールドは必須っぽい。

	//食品、お酒マスターのentity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "merch_id")
	private Integer merchId;
	private String merchName;
	private Integer basePrice;
	@Column(name = "prefecture_id")
	private Integer prefectureId;
	private Integer categoryId;
	private Integer makerId;
	private String merchDetail;
	private String merchContent;
	private String bestBefore;
	private String storeMethod;
	private String madeFrom;
	private String allergen;
	private String alchol;
	private Timestamp registeredTimestamp;

	//地方マスター（ランキング）
	private Integer regionId;
	private Integer firstMerchId;
	private Integer secondMerchId;
	private Integer thirdMerchId;

	//都道府県マスター
	private String prefectureName;

	//カテゴリーマスター
	private String categoryName;

	//メーカーマスター
	private String makerName;

	//おすすめ度
	private Integer rStar;

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getMakerId() {
		return makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
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

	public String getAlchol() {
		return alchol;
	}

	public void setAlchol(String alchol) {
		this.alchol = alchol;
	}

	public Timestamp getRegisteredTimestamp() {
		return registeredTimestamp;
	}

	public void setRegisteredTimestamp(Timestamp registeredTimestamp) {
		this.registeredTimestamp = registeredTimestamp;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getFirstMerchId() {
		return firstMerchId;
	}

	public void setFirstMerchId(Integer firstMerchId) {
		this.firstMerchId = firstMerchId;
	}

	public Integer getSecondMerchId() {
		return secondMerchId;
	}

	public void setSecondMerchId(Integer secondMerchId) {
		this.secondMerchId = secondMerchId;
	}

	public Integer getThirdMerchId() {
		return thirdMerchId;
	}

	public void setThirdMerchId(Integer thirdMerchId) {
		this.thirdMerchId = thirdMerchId;
	}

	public String getPrefectureName() {
		return prefectureName;
	}

	public void setPrefectureName(String prefectureName) {
		this.prefectureName = prefectureName;
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

	public Integer getrStar() {
		return rStar;
	}

	public void setrStar(Integer rStar) {
		this.rStar = rStar;
	}


}
