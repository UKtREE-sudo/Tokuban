package jp.co.bungeejump.tokuban.service;

import java.sql.Date;
import java.util.List;

import jp.co.bungeejump.tokuban.entity.real.MQuestion;
import jp.co.bungeejump.tokuban.entity.real.VMerch;

/**
 * 今日のラッキー特産品のことをやるserviceインタフェース
 * @author 小野
 * @version 0.0.1
 */
public interface FortuneSpecialtyService {

	/**IDに基づいて問題を取得
	 * @param date
	 * @return 取得した問題情報
	 */
	public List<MQuestion> getQuestion(Date date);


	/**IDに基づいて商品を取得
	 * @param date
	 * @return 取得した商品情報
	 */
	public List<VMerch> getMerch(Date date);

}
