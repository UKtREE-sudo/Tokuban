package jp.co.bungeejump.tokuban.service;

import jp.co.bungeejump.tokuban.entity.real.TAddress;
import jp.co.bungeejump.tokuban.form.AddressForm;

/**
 * 配送先関係のサービス
 * @author 大野
 * @version 0.2.0
 */
public interface AddressService {

	/**
	 * 配送先1の情報を取得するメソッド
	 * @param userId ユーザーID
	 * @return 取得した配送先情報
	 */
	public TAddress getAddress1(Integer userId);

	/**
	 * 配送先2の情報を取得するメソッド
	 * @param userId ユーザーID
	 * @return 取得した配送先情報
	 */
	public TAddress getAddress2(Integer userId);


	/**
	 * 配送先1の情報を編集するメソッド
	 * <p>配送先1を新規に設定する場合もこのメソッドを使ってください</p>
	 * @param inputAddress 入力された配送先情報
	 * @param userId ユーザーID
	 * @return 追加した配送先情報
	 */
	public TAddress editAddress1(AddressForm addressForm, Integer userId);

	/**
	 * 配送先2の情報を編集するメソッド
	 * <p>配送先2を新規に設定する場合もこのメソッドを使ってください</p>
	 * @param inputAddress 入力された配送先情報
	 * @param userId ユーザーID
	 * @return 追加した配送先情報
	 */
	public TAddress editAddress2(AddressForm addressForm, Integer userId);

	/**
	 * 配送先1の情報を論理削除するメソッド
	 * @param userId ユーザーID
	 */
	public void deleteAddress1(Integer userId);

	/**
	 * 配送先2の情報を論理削除するメソッド
	 * @param userId ユーザーID
	 */
	public void deleteAddress2(Integer userId);


	/**
	 * 一時的な住所入力に対応するメソッド
	 * @param addressForm 入力された配送先情報
	 * @return 生成されたレコードの自動採番されたID
	 */
	public Integer insertTmpAddress(AddressForm addressForm);

}
