package jp.co.bungeejump.tokuban.dao.real;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.TSettlement;

/**
 * t_settlement用のdao
 * @author 小河原 / 周東
 * @version 0.2.0
 */
@Repository
public interface TSettlementDao extends JpaRepository<TSettlement, Integer> {

	/**
	 * 決済年月を指定して検索
	 * @param settlementTimestamp1
	 * @param settlementTimestamp2
	 * @return
	 */
	@Query("select o from TSettlement o where o.userId = ?1 and o.settlementTimestamp between ?2 and ?3 order by o.settlementTimestamp desc")
	List<TSettlement> findByDate(Integer userId, String settlementTimestamp1, String settlementTimestamp2);

	List<TSettlement> findByUserIdOrderBySettlementTimestampDesc(Integer userId);
}
