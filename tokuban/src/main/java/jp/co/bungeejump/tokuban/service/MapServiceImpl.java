package jp.co.bungeejump.tokuban.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MAchievementDao;
import jp.co.bungeejump.tokuban.dao.real.TAchievementDao;
import jp.co.bungeejump.tokuban.entity.real.MAchievement;
import jp.co.bungeejump.tokuban.entity.real.TAchievement;

/**
 * マップとか実績とかその辺
 * @author 周東
 * @version 0.2.1
 */
@Service
public class MapServiceImpl implements MapService{
	//経験値関連のstatic finalフィールドがあると便利そう。
	private static final Integer TO_BE_PLV2 = 501;
	private static final Integer TO_BE_PLV3 = 2001;
	private static final Integer TO_BE_PLV4 = 8001;
	private static final Integer TO_BE_PLV5 = 300001;

	private static final Integer TO_BE_ALV2 = 10;
	private static final Integer TO_BE_ALV3 = 25;
	private static final Integer TO_BE_ALV4 = 55;
	private static final Integer TO_BE_ALV5 = 120;

	//Lv.5の都道府県は1+2+4+5ポイントもらえる
	private static final Integer AP_OF_PLV2 = 1;
	private static final Integer AP_OF_PLV3 = 2;
	private static final Integer AP_OF_PLV4 = 4;
	private static final Integer AP_OF_PLV5 = 5;

	@Autowired
	private TAchievementDao tDao;

	@Autowired
	private MAchievementDao mDao;

	@Autowired
	private SettlementServiceImpl settlementService;

	/**
	 * 都道府県レベルを取得するメソッド
	 * @param userId 認証情報から取得
	 * @return 0番目は無視。1番目が北海道の県レベル。
	 */
	@Override
	public Integer[] getPLevel(Integer userId) {
		Integer[] pLevel = new Integer[48];
		Integer[] mp = settlementService.getMapPoint(userId);
		for (int i = 1; i <= 47; i ++) {
			if (mp[i] >= TO_BE_PLV5) {
				pLevel[i] = 5;
			} else if (mp[i] >= TO_BE_PLV4) {
				pLevel[i] = 4;
			} else if (mp[i] >= TO_BE_PLV3) {
				pLevel[i] = 3;
			} else if (mp[i] >= TO_BE_PLV2) {
				pLevel[i] = 2;
			} else {
				pLevel[i] = 1;
			}
		}
		return pLevel;
	};

	/**
	 * 16進数の対象都道府県を配列に変換するメソッド
	 * @param targetPrefecture 16進数のやつ
	 * @return 0番目は無視。1番目が北海道。対象なら1、対象じゃなければ0。
	 */
	@Override
	public char[] byteToArray(String targetPrefecture){
		String bitStr = "";
		try {
			byte[] bytes = Hex.decodeHex(targetPrefecture.toCharArray());

			for(byte b : bytes){
			    for(int i=0; i<8; i++){
			        bitStr += String.format("%d", (b & 0x80)/0x80);
			        b <<= 1;
			    }
			}
		} catch (Exception e) {
			//適切な対処が分からない
		}
		return bitStr.toCharArray();
	};

	/**
	 * 実績ボードに表示する内容を取得するメソッド
	 * <p>AchievementDaoでDBから実績情報をとってきて、対象都道府県をbyteToArrayメソッドで変換、
	 * 都道府県レベルを参照してボタンの中身(「達成済み」、「受け取る」、分数のいずれか)を判断する。</p>
	 * @return [実績内容, ボタンの中身]の形でArrayListに入れたやつ
	 */
	public ArrayList<String[]> getAchievementBoard(Integer userId){
		ArrayList<String[]> achievementBoard = new ArrayList<>();
		List<MAchievement> mList = mDao.findAll();

		//都道府県レベルを取得
		Integer[] pLevel = getPLevel(userId);

		for(MAchievement mAchievement : mList) {

			//実績1つごとに実績内容と達成状況を格納
			String[] board = new String[2];
			board[0] = mAchievement.getAchievementDetail();



			//ユーザーIDと実績IDで受け取り状況を取得
			List<TAchievement> tAchievements = tDao.findByCompositePrimaryKey(userId, mAchievement.getAchievementId());

			TAchievement tAchievement;
			//該当のレコードが存在しなかった場合新規に作成
			if (tAchievements.size() == 0) {
				tAchievement = new TAchievement();
				tAchievement.setAchievementId(mAchievement.getAchievementId());
				tAchievement.setUserId(userId);
				tAchievement.setReceived(false);
				addTAchievement(tAchievement);
			} else {
				tAchievement = tAchievements.get(0);
			}

			//対象都道府県を処理できる形に変換
			char[] targetPrefecture = byteToArray(mAchievement.getTargetPrefecture());

			int achievedNumber = 0;
			for (int i = 1; i <= 47; i ++) {
				if (targetPrefecture[i] == 1 || pLevel[i] >= mAchievement.getRequiredPlevel()) {
					achievedNumber += 1;
				}
			}
			if (achievedNumber >= mAchievement.getRequiredNumber()) {
				if (tAchievement.isReceived() == true) {
					board[1] = "達成済み";
				} else {
					board[1] = "受け取る";
				}
			} else {
				board[1] = achievedNumber + "/" + mAchievement.getRequiredNumber();
			}
			achievementBoard.add(board);
		}
		return achievementBoard;
	};

	@Transactional
	public void addTAchievement(TAchievement tAchievement) {
		tDao.save(tAchievement);
	}

	/**
	 * マイページで表示するための実績レベルを取得するメソッド
	 * <p>AchievementDao使ってとってくる情報と、都道府県レベルで算出できると思う。
	 * 都道府県レベルの配列からレベルによる実績ポイントを算出するメソッドを作っても良いかも。お好みで。</p>
	 * @param userId 認証情報から取得
	 * @return 実績レベル
	 */
	@Override
	public Integer getAchievementLevel(Integer userId) {
		List<MAchievement> mList = mDao.findAll();
		Integer[] pLevel = getPLevel(userId);
		Integer ap = 0;

		//都道府県レベルに応じたポイントを加算する
		for (int i = 1; i <= 47; i ++) {
			switch (pLevel[i]) {
			case 5:
				ap += AP_OF_PLV5;
			case 4:
				ap += AP_OF_PLV4;
			case 3:
				ap += AP_OF_PLV3;
			case 2:
				ap += AP_OF_PLV2;
			default:
				break;
			}
		}

		//実績の達成度に応じたポイントを加算する
		for(MAchievement mAchievement : mList) {
			Integer achievementId = mAchievement.getAchievementId();
			List<TAchievement> tAchievements = tDao.findByCompositePrimaryKey(userId, achievementId);
			char[] targetPrefecture = byteToArray(mAchievement.getTargetPrefecture());
			int achievedNumber = 0;
			for (int i = 1; i <= 47; i ++) {
				if (targetPrefecture[i] == '1' && pLevel[i] >= mAchievement.getRequiredPlevel()) {
					achievedNumber += 1;
				}
			}
			if (achievedNumber >= mAchievement.getRequiredNumber()) {
				if (tAchievements.get(0).isReceived() == true) {
					ap += 1;
				}
			}
		}

		Integer aLv = 1;
		//APから実績レベルを判断
		if (ap >= TO_BE_ALV5) {
			aLv = 5;
		} else if (ap >= TO_BE_ALV4) {
			aLv = 4;
		} else if (ap >= TO_BE_ALV3) {
			aLv = 3;
		} else if (ap >= TO_BE_ALV2) {
			aLv = 2;
		}

		return aLv;
	}

	/**
	 * クーポンの累計獲得枚数を取得するメソッド
	 * @param userId 認証情報から取得
	 * @return クーポンの累計獲得枚数（消費したかは考えなくておk）
	 */
	@Override
	public Integer getAchievedCoupons(Integer userId) {
		Integer aLv = getAchievementLevel(userId);
		Integer coupons = 0;
		if (aLv >= 2) {
			for (int i = 2; i <= aLv; i ++) {
				coupons += i;
			}
		}
		return coupons;
	};


}
