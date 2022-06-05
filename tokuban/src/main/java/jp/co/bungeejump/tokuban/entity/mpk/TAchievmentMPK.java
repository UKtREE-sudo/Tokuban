package jp.co.bungeejump.tokuban.entity.mpk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 小河原
 * @version 0.0.1
 */
@Embeddable //複合キーにするためのアノテーション
public class TAchievmentMPK implements Serializable {

	@Column(name="user_id")
	private Integer userId;

	@Column(name="achievement_id")
	private Integer achievementId;

	// getter/setter
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

	//使わないかもしれないけど作れって書いてあったから作った
	@Override
	public int hashCode() {
		return Objects.hash(achievementId, userId);
	}

	//同上
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TAchievmentMPK other = (TAchievmentMPK) obj;
		return Objects.equals(achievementId, other.achievementId) && Objects.equals(userId, other.userId);
	}

}
