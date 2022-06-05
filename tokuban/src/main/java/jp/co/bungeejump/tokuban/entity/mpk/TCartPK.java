package jp.co.bungeejump.tokuban.entity.mpk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 複合主キーが書いてあるやつ
 * @author 末永
 * @version 0.0.1
 *
 */

@Embeddable
public class TCartPK implements Serializable {
	@Column (name="user_id")
    private int userId;
	@Column (name="merch_id")
    private int merchId;
    public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMerchId() {
		return merchId;
	}
	public void setMerchId(int merchId) {
		this.merchId = merchId;
	}

}
