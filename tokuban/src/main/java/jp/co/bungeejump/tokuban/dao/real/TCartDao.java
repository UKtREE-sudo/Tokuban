package jp.co.bungeejump.tokuban.dao.real;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.bungeejump.tokuban.entity.mpk.TCartPK;
import jp.co.bungeejump.tokuban.entity.real.TCart;

/**
 * t_cartテーブルにアクセスするためのDAO
 * @author 末永
 * @version 0.0.1
 * 複合主キーやったので
 * Contorollerもいじらないといけなくなるかも・・・？
 *
 */
@Repository
public interface TCartDao extends JpaRepository<TCart, TCartPK> {

	//ユーザーIDで検索かけて全権取得するメソッド
	List<TCart> findByUserId(int userId);

	@Transactional
	@Modifying
	@Query("delete from TCart o where o.userId = :userId and o.merchId = :merchId")
	void deleteByUserIdAndMerchId(@Param("userId") Integer userId, @Param("merchId") Integer merchId);

	@Modifying
	void deleteByUserId(Integer userId);

}
