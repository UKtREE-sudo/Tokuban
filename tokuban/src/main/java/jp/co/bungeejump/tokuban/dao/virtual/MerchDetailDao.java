package jp.co.bungeejump.tokuban.dao.virtual;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.bungeejump.tokuban.entity.real.VMerch;
import jp.co.bungeejump.tokuban.entity.virtual.MerchDetail;
import jp.co.bungeejump.tokuban.entity.virtual.VMerchCategory;

/**
 * 商品詳細を取得するDao
 * @author 高根
 * 担当 高根のクエリはここに書きます
 */
@Repository
public interface MerchDetailDao extends JpaRepository<MerchDetail, Integer> {

	//IDを指定して必要な情報を取得できればおk。
	//必要な情報（カラム）はMerchDetailのフィールドにする。
	//多分クエリ書sかないといけないのと、食品とお酒のテーブルが違う問題が大変だと思うので頑張ってほしい（無責任）

	//List<VMerchCategory> findBymerchId(Integer merch_id);

	List<VMerch> findByRegionId(Integer regionId);

	//商品詳細 小河原修正済み
	/*@Modifying
	@Query("select c from MerchDetail c where c.merch_id = :merch_id")
	public MerchDetail findByMerchDetail(Integer merch_id);*/

	//星の集計 小河原一時コメントアウト
	//t_purchase、Starを参照しているけど高根担当のためここに入れてます
	/*@Modifying
	@Query("select 1 as 'star', count(*) as 'count' from t_purchase where merch_id = ?1 and r_star = 1 " +
			"union " +
			"select 2 as 'star', count(*) as 'count' from t_purchase where merch_id = ?1 and r_star = 2 " +
			"union " +
			"select 3 as 'star', count(*) as 'count' from t_purchase where merch_id = ?1 and r_star = 3 " +
			"union " +
			"select 4 as 'star', count(*) as 'count' from t_purchase where merch_id = ?1 and r_star = 4 " +
			"union " +
			"select 5 as 'star', count(*) as 'count' from t_purchase where merch_id = ?1 and r_star = 5")
	public List<Star> CountStarByMerchId(Integer merchId);*/

	//カテゴリーと商品名（全て以外）検索クエリ 小河原修正済み
	@Modifying
	@Query(value = "select * from v_merch_category c where c.category_name = ?1 and c.merch_name like %?2% ", nativeQuery = true)
	public List<VMerchCategory> findByCategoryMerch(String category, String inputText);

	//カテゴリーとお酒全て検索クエリ 小河原修正済み
	@Modifying
	@Query(value = "select * from v_merch_category c where "
			+ "c.category_name = 'ビール' or c.category_name = '焼酎' or c.category_name = '日本酒' or c.category_name = 'ウィスキー'"
			+ "and c.merch_name like %?1%", nativeQuery = true)
	public List<VMerchCategory> findByLiquorCategoryMerch(String inputText);

	//カテゴリーと食品全て検索クエリ 小河原修正済み
	@Modifying
	@Query(value = "select * from v_merch_category c where "
			+ "c.category_name = 'お菓子' or c.category_name = '麺類' or c.category_name = 'お肉' or c.category_name = '海鮮'"
			+ "c.category_name = '調味料' or c.category_name = '飲み物'"
			+ "and c.merch_name like %?1%", nativeQuery = true)
	public List<VMerchCategory> findByFoodCategoryMerch(String inputText);

	//カテゴリー(食品、お酒全て以外)検索 小河原修正済み
	@Modifying
	@Query(value = "select * from v_merch_category c where c.category_name = ?1", nativeQuery = true)
	public List<VMerchCategory> findByCategory(String category);

	/*//商品名検索 小河原修正済み
	@Modifying
	@Query("select c from VMerchCategory c where c.merchName like %:merchName%")
	public List<VMerchCategory> findByMerch(String merchName);*/

	//商品名検索 小河原修正済み
		@Query("select v from VMerch v where v.merchName like %?1%")
		public List<VMerch> findByMerch(String merchName);

	//おすすめ度ソート用 小河原修正済み
	@Query(value = "select * from v_merch_category c order by c.merch_id", nativeQuery = true)
	public List<VMerchCategory> findAllOrderByStar(Integer merchId);

	//価格高い順ソート 小河原修正済み
	@Query(value = "select * from v_merch_category c order by c.basePrice asc", nativeQuery = true)
	public List<VMerchCategory> findAllOrderByPriceUp(Integer merchId);

	//価格低い順ソート 小河原修正済み
	@Query(value = "select * from v_merch_category c order by c.basePrice desc", nativeQuery = true)
	public List<VMerchCategory> findAllOrderByPriceDown(Integer merchId);

	//新着順ソート
	@Query(value = "select * from v_merch_category c order by c.registered_timestamp", nativeQuery = true)
	public List<VMerchCategory> findAllOrderByTime(Integer merchId);

}
