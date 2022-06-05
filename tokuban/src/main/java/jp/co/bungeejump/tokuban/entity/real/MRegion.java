package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * m_regionのやつ。グランプリで使う。
 * @author 小野
 *
 */
@Entity
@Table(name = "m_region")
public class MRegion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer regionId;
	private String regionName;
	private Integer firstMerchId;
	private Integer secondMerchId;
	private Integer thirdMerchId;
	private Integer fourthMerchId;
	private Integer fifthMerchId;
	private Integer sixthMerchId;
	private Integer seventhMerchId;
	private Integer eighthMerchId;
	private Integer ninthMerchId;
	private Integer tenthMerchId;
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
	public Integer getFourthMerchId() {
		return fourthMerchId;
	}
	public void setFourthMerchId(Integer fourthMerchId) {
		this.fourthMerchId = fourthMerchId;
	}
	public Integer getFifthMerchId() {
		return fifthMerchId;
	}
	public void setFifthMerchId(Integer fifthMerchId) {
		this.fifthMerchId = fifthMerchId;
	}
	public Integer getSixthMerchId() {
		return sixthMerchId;
	}
	public void setSixthMerchId(Integer sixthMerchId) {
		this.sixthMerchId = sixthMerchId;
	}
	public Integer getSeventhMerchId() {
		return seventhMerchId;
	}
	public void setSeventhMerchId(Integer seventhMerchId) {
		this.seventhMerchId = seventhMerchId;
	}
	public Integer getEighthMerchId() {
		return eighthMerchId;
	}
	public void setEighthMerchId(Integer eighthMerchId) {
		this.eighthMerchId = eighthMerchId;
	}
	public Integer getNinthMerchId() {
		return ninthMerchId;
	}
	public void setNinthMerchId(Integer ninthMerchId) {
		this.ninthMerchId = ninthMerchId;
	}
	public Integer getTenthMerchId() {
		return tenthMerchId;
	}
	public void setTenthMerchId(Integer tenthMerchId) {
		this.tenthMerchId = tenthMerchId;
	}

}