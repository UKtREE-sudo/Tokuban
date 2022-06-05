package jp.co.bungeejump.tokuban.dao.real;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.bungeejump.tokuban.entity.real.TPurchase;

/**
 * t_purchase用のdao
 * @author 小河原 / 周東
 * @version 0.2.0
 */
public interface TPurchaseDao extends JpaRepository<TPurchase, Integer> {

	//使わないかも
	List<TPurchase> findBySettlementId(Integer settlementId);

	/**
	 * 決済IDと商品名で絞り込み検索を行うメソッド
	 * @param settlementId
	 * @param merchName 呼び出し元で前後に%をつける
	 * @return
	 */
	@Query("select p from TPurchase p join VMerch m on p.merchId = m.merchId where p.settlementId = ?1 and m.merchName like ?2")
	List<TPurchase> findBySettlementIdAndMerchName(Integer settlementId, String merchName);

	/**
	 * 決済IDと都道府県名で絞り込み検索を行うメソッド
	 * @param settlementId
	 * @param prefectureName 呼び出し元で前後に%をつける
	 * @return
	 */
	@Query("select p from TPurchase p join VMerch m on p.merchId = m.merchId where p.settlementId = ?1 and m.prefectureName like ?2")
	List<TPurchase> findBySettlementIdAndPrefectureName(Integer settlementId, String prefectureName);
}
