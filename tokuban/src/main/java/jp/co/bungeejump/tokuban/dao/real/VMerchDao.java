package jp.co.bungeejump.tokuban.dao.real;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.bungeejump.tokuban.entity.real.VMerch;

/**
 * v_merch用のdao
 * <p>基本的にIDによる検索しかやらないはず</p>
 * @author 周東
 * @version 0.0.1
 */
public interface VMerchDao extends JpaRepository<VMerch, Integer>{

	/**
	 * 商品IDから商品概要を取得するメソッド
	 * @param merchId
	 * @return 取得した商品概要
	 */
	VMerch getById(Integer merchId);

}
