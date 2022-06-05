package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * m_prefecture用のentity
 * <p>だいすきナインでしか使わないんじゃないかな</p>
 * @author 周東
 * @version 0.0.0
 */
@Entity
public class MPrefecture {

	@Id
	private Integer prefectureId;
	private String prefectureName;
	private Integer regionId;

	public Integer getPrefectureId() {
		return prefectureId;
	}

	public void setPrefectureId(Integer prefectureId) {
		this.prefectureId = prefectureId;
	}

	public String getPrefectureName() {
		return prefectureName;
	}

	public void setPrefectureName(String prefectureName) {
		this.prefectureName = prefectureName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
}
