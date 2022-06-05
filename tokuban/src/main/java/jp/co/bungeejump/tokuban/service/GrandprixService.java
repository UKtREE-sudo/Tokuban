package jp.co.bungeejump.tokuban.service;

import java.util.List;

import jp.co.bungeejump.tokuban.entity.real.VMerch;

/**
 * グランプリ関係のことをやるserviceインタフェース
 * @author 小野
 * @version 0.0.1
 */
public interface GrandprixService {

	/**
	 * 指定の地方のトップ3を取得する
	 * <p>商品詳細画面で出すやつ</p>
	 * @param regionId
	 * @return 取得したグランプリ情報
	 */
	public List<VMerch> getTopThree(Integer regionId);

	/**
	 * 指定の地方のトップ10を取得する
	 * <p>これが本物のグランプリ。</p>
	 * @param regionId
	 * @return 取得したグランプリ情報
	 */
	public List<VMerch> getTopTen(Integer regionId);

}
