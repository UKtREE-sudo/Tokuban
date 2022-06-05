package jp.co.bungeejump.tokuban.dao.real;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.TNine;

/**
 *
 * @author 末永
 * @version 0.0.1
 *
 */
@Repository
public interface TNineDao  extends JpaRepository<TNine, Integer>{


	List<TNine> findByUserId(Integer userId);

	/**
	 * センターの商品を変更するメソッド
	 * @param merchId 商品ID
	 * @param userId ユーザーID
	 */
	@Modifying
	@Query("update TNine t set t.favCenterId = ?1 where t.userId = ?2")
	void updateTNineCenter(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favHokkaidoId = ?1 where t.userId = ?2")
	void updateTNineHokkaido(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favTohokuId = ?1 where t.userId = ?2")
	void updateTNineTohoku(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favKantoId = ?1 where t.userId = ?2")
	void updateTNineKanto(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favChubuId = ?1 where t.userId = ?2")
	void updateTNineChubu(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favKinkiId = ?1 where t.userId = ?2")
	void updateTNineKinki(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favChugokuId = ?1 where t.userId = ?2")
	void updateTNineChugoku(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favShikokuId = ?1 where t.userId = ?2")
	void updateTNineShikoku(Integer merchId, Integer userId);

	@Modifying
	@Query("update TNine t set t.favKyushuId = ?1 where t.userId = ?2")
	void updateTNineKyushu(Integer merchId, Integer userId);
}
