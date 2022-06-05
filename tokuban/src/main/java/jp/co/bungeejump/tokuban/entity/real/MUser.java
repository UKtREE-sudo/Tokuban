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

/**
 * m_userテーブルに対応するentity
 * @author 周東
 * @version 0.0.2
 */
@Entity
@Table(name = "m_user")
public class MUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String mailAddress;
	private String password;
	private String nickname;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name = "address1_id")
	private Integer address1Id;
	@Column(name = "address2_id")
	private Integer address2Id;
	private String role;
	private boolean isActive;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getAddress1Id() {
		return address1Id;
	}
	public void setAddress1Id(Integer address1Id) {
		this.address1Id = address1Id;
	}
	public Integer getAddress2Id() {
		return address2Id;
	}
	public void setAddress2Id(Integer address2Id) {
		this.address2Id = address2Id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
