package jp.co.bungeejump.tokuban.service;

import jp.co.bungeejump.tokuban.entity.real.MUser;
import jp.co.bungeejump.tokuban.form.InfoMForm;

/**
 * 会員情報を色々する
 * @author 周東 / ちょっと小河原
 * @version 0.3.0
 */

public interface InfoMService {

	/**
	 * 会員情報取得メソッド
	 * @param userId
	 * @return
	 */
	public MUser findById(Integer userId);

	/**
	 * 新規会員登録フォームを受け取ってDBに登録するメソッド
	 * @param form 新規会員登録フォームに入力された情報
	 */
	public void register(InfoMForm form);

	/**
	 * DBから会員情報を論理削除するメソッド
	 */
	public void resign(Integer userId);

	/**
	 * 会員情報を更新するメソッド
	 * @param userId ユーザーID
	 * @param form 会員情報編集フォームに入力された情報
	 */
	public void editInfoM(Integer userId, InfoMForm form);

	/**
	 * アップグレードするメソッド
	 * <p>アップグレードできるかどうかはControllerで判断してください</p>
	 * @param userId ユーザーID
	 */
	public void bePremium(Integer userId);

	/**
	 * 入力されたメールアドレスが使用可能なものであるかチェックするメソッド（新規会員用）
	 * <p>すでに他の会員のメールアドレスとして登録されているものでなければOK</p>
	 * @param mailAddress
	 * @return 使用可能ならtrue, そうでなければfalseを返す
	 */
	public boolean isUsableMailAddress(String mailAddress);

	/**
	 * 入力されたメールアドレスが使用可能なものであるかチェックするメソッド（既存会員用）
	 * <o>すでに他の会員のメールアドレスとして登録されているものでなければOK</p>
	 * @param mailAddress
	 * @param userId
	 * @return
	 */
	public boolean isUsableMailAddress(String mailAddress, Integer userId);

	/**
	 * 入力された誕生日が妥当なものであるかチェックするメソッド
	 * @param year
	 * @param month
	 * @param day
	 * @return 妥当な入力ならtrue, そうでなければfalsemを返す
	 */
	public boolean isValidBirthday(String year, String month, String day);
}
