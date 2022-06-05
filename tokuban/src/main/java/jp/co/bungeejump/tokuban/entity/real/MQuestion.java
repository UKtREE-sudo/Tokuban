package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * m_questionのやつ
 * @author 小野
 * @version 0.0.1
 */
@Entity
public class MQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer qustionId;
	private String questionDetail;
	private String firstOption;
	private String secondOption;
	private boolean isAboutLiquor;
	public Integer getQustionId() {
		return qustionId;
	}
	public void setQustionId(Integer qustionId) {
		this.qustionId = qustionId;
	}
	public String getQuestionDetail() {
		return questionDetail;
	}
	public void setQuestionDetail(String questionDetail) {
		this.questionDetail = questionDetail;
	}
	public String getFirstOption() {
		return firstOption;
	}
	public void setFirstOption(String firstOption) {
		this.firstOption = firstOption;
	}
	public String getSecondOption() {
		return secondOption;
	}
	public void setSecondOption(String secondOption) {
		this.secondOption = secondOption;
	}
	public boolean isAboutLiquor() {
		return isAboutLiquor;
	}
	public void setAboutLiquor(boolean isAboutLiquor) {
		this.isAboutLiquor = isAboutLiquor;
	}
}
