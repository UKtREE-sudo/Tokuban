package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import jp.co.bungeejump.tokuban.entity.mpk.TAchievmentMPK;

/**
 * t_achievementのやつ
 * @author 小河原
 * @version 0.0.1
 */
@Entity
@Table(name = "t_achievement")
@IdClass(TAchievmentMPK.class)
public class TAchievement {

	// フィールド↓
	@Id
	@Column(name = "user_id")
	private Integer userId;

	@Id
	@Column(name = "achievement_id")
	private Integer achievementId;

	@Column(name = "is_received")
	private boolean isReceived;
	// フィールド↑

	// setter/getter↓
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}

	public boolean isReceived() {
		return isReceived;
	}

	public void setReceived(boolean isReceived) {
		this.isReceived = isReceived;
	}
	// setter/getter↑

}
