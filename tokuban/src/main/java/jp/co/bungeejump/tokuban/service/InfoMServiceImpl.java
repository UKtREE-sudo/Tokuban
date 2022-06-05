package jp.co.bungeejump.tokuban.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MAchievementDao;
import jp.co.bungeejump.tokuban.dao.real.MUserDao;
import jp.co.bungeejump.tokuban.dao.real.TAchievementDao;
import jp.co.bungeejump.tokuban.dao.real.TNineDao;
import jp.co.bungeejump.tokuban.entity.real.MAchievement;
import jp.co.bungeejump.tokuban.entity.real.MUser;
import jp.co.bungeejump.tokuban.entity.real.TAchievement;
import jp.co.bungeejump.tokuban.entity.real.TNine;
import jp.co.bungeejump.tokuban.form.InfoMForm;

/**
 * 会員情報に関連するサービスを提供します
 * @author 周東 / ちょっと小河原
 * @version 0.3.0
 */
@Service
public class InfoMServiceImpl implements InfoMService{

	@Autowired
	MUserDao mUserDao;

	@Autowired
	TNineDao tNineDao;

	@Autowired
	MAchievementDao maDao;

	@Autowired
	TAchievementDao taDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * 会員情報取得メソッド
	 * @param userId
	 * @return
	 */
	public MUser findById(Integer userId) {

		return mUserDao.getById(userId);
	}

	/**
	 * 新規会員登録フォームを受け取ってDBに登録するメソッド
	 * <p>だいすきナインと実績トランザクションにもレコードを追加する<br>
	 * 入力チェックはcontrollerでやってください（合ってるか知らんけど）</p>
	 * @param form 新規会員登録フォームに入力された情報
	 */
	@Override
	public void register(InfoMForm form) {
		String encodedPassword = passwordEncoder.encode(form.getPassword1());
		String birthdayStr = form.getBirthYear() + "-" + form.getBirthMonth() + "-" + form.getBirthDate();
		Date birthday = Date.valueOf(birthdayStr);
		MUser newUser = new MUser();
		newUser.setMailAddress(form.getMailAddress());
		newUser.setPassword(encodedPassword);
		newUser.setNickname(form.getNickname());
		newUser.setBirthday(birthday);
		newUser.setRole("FREE");
		newUser.setActive(true);
		mUserDao.save(newUser);
		MUser registeredUser = mUserDao.findByMailAddress(form.getMailAddress()).get(0);
		Integer userId = registeredUser.getUserId();

		//だいすきナイン
		TNine tNine = new TNine();
		tNine.setUserId(userId);
		tNineDao.save(tNine);

		//実績
		List<MAchievement> maList = maDao.findAll();
		for (MAchievement ma : maList) {
			TAchievement tAchievement = new TAchievement();
			tAchievement.setAchievementId(ma.getAchievementId());
			tAchievement.setUserId(userId);
			tAchievement.setReceived(false);
			taDao.save(tAchievement);
		}

	};

	/**
	 * DBから会員情報を論理削除するメソッド
	 * @param userId
	 */
	@Transactional
	@Override
	public void resign(Integer userId) {
		mUserDao.updateMUserIsActive(false, userId);
	};

	/**
	 * 会員情報を更新するメソッド
	 * @param userId ユーザーID
	 * @param form 会員情報編集フォームに入力された情報
	 */
	@Transactional
	@Override
	public void editInfoM(Integer userId, InfoMForm form) {
		if (form.getMailAddress().length() > 0) {
			MUser mUser = mUserDao.getById(userId);
			if (mUser.getMailAddress().equals(form.getMailAddress()) == false) {
				mUserDao.updateMUserMailAddress(form.getMailAddress(), userId);
			}
		}
		if (form.getPassword1().length() > 0) {
			String encodedPassword = passwordEncoder.encode(form.getPassword1());
			mUserDao.updateMUserPassword(encodedPassword, userId);
		}
		if (form.getNickname().length() > 0) {
			mUserDao.updateMUserNickname(form.getNickname(), userId);
		}

	};

	/**
	 * アップグレードするメソッド
	 * <p>アップグレードできるかどうかはControllerで判断してください</p>
	 * @param userId ユーザーID
	 */
	@Override
	@Transactional
	public void bePremium(Integer userId) {
		mUserDao.updateMUserRole("PAID", userId);

	};

	@Override
	public boolean isValidBirthday(String year, String month, String day) {
		boolean isValid = false;
		//年が1000〜9999
		try {
			int yearNum = Integer.parseInt(year);
			if (1000 <= yearNum && yearNum <= 9999) {
				//月が1〜12で、日が月ごとの妥当な値
				int monthNum = Integer.parseInt(month);
				int dayNum = Integer.parseInt(day);
				switch (monthNum) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					if (1 <= dayNum && dayNum <= 31) {
						isValid = true;
					}
					break;

				case 4:
				case 6:
				case 9:
				case 11:
					if (1 <= dayNum && dayNum <= 30) {
						isValid = true;
					}
					break;

				case 2:
					if (1 <= dayNum && dayNum <= 29) {
						isValid = true;
						//うるう年じゃなくても29日可になってます。バンジージャンプ先生の次回作に期待！
					}
					break;
				default:
					//普通に不正な入力なので対処不要。falseを返す。
					break;
				}
			}

		} catch (NumberFormatException e) {
			//エラー出てもOKなのでそのままfalseを返す
		}
		return isValid;
	}

	@Override
	public boolean isUsableMailAddress(String mailAddress) {
		boolean isUsable = false;
		List<MUser> mUser = mUserDao.findByMailAddress(mailAddress);
		if (mUser.size() == 0) {
			isUsable = true;
		}
		return isUsable;
	}

	@Override
	public boolean isUsableMailAddress(String mailAddress, Integer userId) {
		boolean isUsable = false;
		List<MUser> mUser = mUserDao.findByMailAddress(mailAddress);
		if (mUser.size() == 0) {
			isUsable = true;
		} else {
			if (mUser.get(0).getUserId().equals(userId)) {
				isUsable = true;
			}
		}
		return isUsable;
	}

}
