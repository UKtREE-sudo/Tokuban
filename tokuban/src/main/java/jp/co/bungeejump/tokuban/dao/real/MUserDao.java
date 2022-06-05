package jp.co.bungeejump.tokuban.dao.real;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.MUser;


/**
 * m_userテーブルとやりとりする
 * @author 周東
 * @version 0.2.0
 */
@Repository
public interface MUserDao extends JpaRepository<MUser, Integer>{

	List<MUser> findByMailAddress(String mailAddress);

	@Modifying
	@Query("update MUser m set m.mailAddress = ?1 where m.userId = ?2")
	void updateMUserMailAddress(String mailAddress, Integer userId);

	@Modifying
	@Query("update MUser m set m.password = ?1 where m.userId = ?2")
	void updateMUserPassword(String encodedPassword, Integer userId);

	@Modifying
	@Query("update MUser m set m.nickname = ?1 where m.userId = ?2")
	void updateMUserNickname(String nickname, Integer userId);

	/**
	 * 会員の権限を変更するメソッド
	 * @param role 変更後の権限
	 * @param userId
	 */
	@Modifying
	@Query("update MUser m set m.role = ?1 where m.userId = ?2")
	void updateMUserRole(String role, Integer userId);

	@Modifying
	@Query("update MUser m set m.isActive = ?1 where m.userId = ?2")
	void updateMUserIsActive(boolean isActive, Integer userId);

	//ここから上InfoMService関係（周東）
	//ここから下AddressService関係（大野）

	/**
	 * 配送先IDを変更するメソッド
	 * @param address1Id
	 * @param userId
	 */
	@Modifying
	@Query("update MUser m set m.address1Id = ?1 where m.userId = ?2")
	void updateAddress1(Integer address1Id, Integer userId);

	@Modifying
	@Query("update MUser m set m.address2Id = ?1 where m.userId = ?2")
	void updateAddress2(Integer address2Id, Integer userId);

	/**
	 * 実質的なUPDATEメソッド
	 * @param userId
	 */
	@Modifying
	@Query("update MUser m set m.address1Id = null where m.userId = ?1")
	void updateAddress1null(Integer userId);

	@Modifying
	@Query("update MUser m set m.address2Id = null where m.userId = ?1")
	void updateAddress2null(Integer userId);
}
