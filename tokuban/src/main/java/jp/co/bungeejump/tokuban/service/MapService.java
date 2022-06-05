package jp.co.bungeejump.tokuban.service;

import java.util.ArrayList;

/**
 * マップ、実績関連のserviceクラス
 * @author 周東
 * @version 0.0.2
 */

public interface MapService {

	/**
	 * 都道府県レベルを取得するメソッド
	 * @param userId 認証情報から取得
	 * @return 0番目は無視。1番目が北海道の県レベル。
	 */
	public Integer[] getPLevel(Integer userId);

	/**
	 * 16進数の対象都道府県を配列に変換するメソッド
	 * @param targetPrefecture 16進数のやつ
	 * @return 0番目は無視。1番目が北海道。対象なら1、対象じゃなければ0。
	 */
	public char[] byteToArray(String targetPrefecture);

	/**
	 * 実績ボードに表示する内容を取得するメソッド
	 * <p>AchievementDaoでDBから実績情報をとってきて、対象都道府県をbyteToArrayメソッドで変換、
	 * 都道府県レベルを参照してボタンの中身(「達成済み」、「受け取る」、分数のいずれか)を判断する。</p>
	 * @return [実績内容, ボタンの中身]の形でArrayListに入れたやつ
	 */
	public ArrayList<String[]> getAchievementBoard(Integer userId);

	/**
	 * マイページで表示するための実績レベルを取得するメソッド
	 * <p>AchievementDao使ってとってくる情報と、都道府県レベルで算出できると思う。
	 * 都道府県レベルの配列からレベルによる実績ポイントを算出するメソッドを作っても良いかも。お好みで。</p>
	 * @param userId 認証情報から取得
	 * @return 実績レベル
	 */
	public Integer getAchievementLevel(Integer userId);

	/**
	 * クーポンの累計獲得枚数を取得するメソッド
	 * @param userId 認証情報から取得
	 * @return クーポンの累計獲得枚数（消費したかは考えなくておk）
	 */
	public Integer getAchievedCoupons(Integer userId);
}
