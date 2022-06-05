package jp.co.bungeejump.tokuban.service;

import jp.co.bungeejump.tokuban.entity.real.VMerch;

/**
 * だいすきナインのやつ
 * @author 周東
 * @version 0.2.0
 */

public interface FavoriteNineService {

	/**
	 * 指定の商品をセンターにするメソッド
	 * @param user_id
	 * @param merch_id
	 */
	void selectAsCenter(Integer userId, Integer merchId);

	/**
	 * 指定の商品を地方代表にするメソッド
	 * @param userId
	 * @param merchId
	 */
	void addFavorite(Integer userId, Integer merchId);

	/**
	 * 指定のユーザーのだいすきナイン情報を取得するメソッド
	 * @param userId
	 * @return 0番目がセンター、1番目が北海道地方代表、...、7番目が九州地方代表の商品情報。未登録の場合はnullが入っています。
	 */
	VMerch[] findById(Integer userId);

	/**
	 * 推し変ボタンが押された役職を空席にするメソッド
	 * @param userId
	 * @param column DBに登録されているカラム名。"fav_center_id"など。
	 */
	void deleteFavorite(Integer userId, String column);
}
