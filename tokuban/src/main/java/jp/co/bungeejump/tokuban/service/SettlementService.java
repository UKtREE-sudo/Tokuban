package jp.co.bungeejump.tokuban.service;

import java.util.List;

import jp.co.bungeejump.tokuban.entity.virtual.PurchaseHistoryDisplay;
import jp.co.bungeejump.tokuban.form.CartForm;
import jp.co.bungeejump.tokuban.form.PurchaseHistoryForm;

/**
 * 決済関係のサービスを提供します
 * @author 小河原 / 周東 / 末永
 * @version 0.2.0
 *
 */
public interface SettlementService {

	/**
	 * 取得した年月から検索用のフォーマットを作成し返すメソッド
	 * <p>"yyyy-mm-01 00:00:00"の形式。<br>
	 * 年のみが選択された場合はその年の1月1日から翌年の1月1日までを返す</p>
	 * @param year 選択されなかった場合は0 こっちの処理は呼び出し元でやる
	 * @param month 選択されなかった場合は0
	 * @return 0番目の要素が開始日時、1番目の要素が終了日時
	 */
	public String[] makeDate(int year, int month);

	/**
	 * 検索条件を指定して購入履歴を取得するメソッド
	 * @param userId
	 * @param form
	 * @return 購入履歴の情報が新しい順に格納されたリスト
	 */
	public List<PurchaseHistoryDisplay> showPurchaseHistory(Integer userId, PurchaseHistoryForm form);

	/**
	 * これまでに使用した累計のクーポン枚数を算出するメソッド
	 * @return
	 */
	public Integer getUsedCoupons(Integer userId);

	/**
	 * MPを取得するメソッド
	 * @param userId 認証情報から取得
	 * @return 配列の0番目無視、1番目北海道 ... 都道府県累計購入金額
	 */
	public Integer[] getMapPoint(Integer userId);

	/**
	 * 決済を行うメソッド
	 * @param userId
	 * @param addressId
	 * @param coupons
	 * @return 自動採番された決済ID
	 */
	public Integer makeSettlement(Integer userId, Integer addressId, Integer coupons);

	/**
	 * 指定の購入情報を購入履歴トランザクションに入れるメソッド
	 * @param purchasedMerchList
	 * @param settlementId
	 */
	public void insertPurchaseHistory(List<CartForm> purchasedMerchList, Integer settlementId);

}
























