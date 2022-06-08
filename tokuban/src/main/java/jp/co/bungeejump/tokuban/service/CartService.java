package jp.co.bungeejump.tokuban.service;

import java.util.List;

import jp.co.bungeejump.tokuban.form.CartForm;

/**
 * カート関係の処理をやるService
 * @author 末永
 * @version 0.2.0
 */

public interface CartService {

	//

	/**
	 * カートに追加するメソッド
	 * @param userId 認証情報から取得
	 * @param form 商品IDと個数のみ
	 */
	public void addToCart(Integer userId, CartForm form);

	/**
	 * カート情報を読みこむメソッド
	 * @param userId 認証情報から取得
	 * @return 全フィールドに値が入ったform
	 */
	public List<CartForm> seeCart(Integer userId);

	/**
	 * 商品IDを指定してカートから削除するメソッド
	 * <p>TCartDaoを呼び出して指定の商品を削除し、更新後のカート情報を取得して返す。</p>
	 * @param userId 認証情報から取得
	 * @param merchId 商品ID
	 * @return
	 */
	public List<CartForm> deleteFromCart(Integer userId, Integer merchId);

	/**
	 * ユーザーIDを指定してカートの全ての情報を削除するメソッド
	 * @param userId
	 */
	public void deleteAllFromCart(Integer userId);
}
