package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * m_achievement用のentity
 * @author 周東
 * @version 0.0.3
 */
@Entity
@Table(name = "m_achievement")
public class MAchievement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "achievement_id")
	private Integer achievementId;
	@Column(name = "achievement_detail")
	private String achievementDetail;
	@Column(name = "achievement_point")
	private Integer achievementPoint;
	@Column(name = "target_prefecture")
	private String targetPrefecture;
	@Column(name = "requiredPlevel")
	private Integer requiredPlevel;
	@Column(name = "requiredNumber")
	private Integer requiredNumber;
	public Integer getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}
	public String getAchievementDetail() {
		return achievementDetail;
	}
	public void setAchievementDetail(String achievementDetail) {
		this.achievementDetail = achievementDetail;
	}
	public Integer getAchievementPoint() {
		return achievementPoint;
	}
	public void setAchievementPoint(Integer achievementPoint) {
		this.achievementPoint = achievementPoint;
	}
	public String getTargetPrefecture() {
		return targetPrefecture;
	}
	public void setTargetPrefecture(String targetPrefecture) {
		this.targetPrefecture = targetPrefecture;
	}
	public Integer getRequiredPlevel() {
		return requiredPlevel;
	}
	public void setRequiredPlevel(Integer requiredPlevel) {
		this.requiredPlevel = requiredPlevel;
	}
	public Integer getRequiredNumber() {
		return requiredNumber;
	}
	public void setRequiredNumber(Integer requiredNumber) {
		this.requiredNumber = requiredNumber;
	}

}
