package jp.co.bungeejump.tokuban.entity.real;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 認証用のEntity
 * @author 周東
 * @version 0.0.1
 *
 */
public class MemberDetails extends User{

	//username, passwordのフィールドは要らない
	//private String mailAddress;
	//private String password;

	private Integer userId;

	public MemberDetails(String mailAddress, String password, Collection<? extends GrantedAuthority> authorities) {
		super(mailAddress, password, authorities);
	}

	//usernameの実態はメールアドレスなので
	public String getMailAddress() {
		return this.getUsername();
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
