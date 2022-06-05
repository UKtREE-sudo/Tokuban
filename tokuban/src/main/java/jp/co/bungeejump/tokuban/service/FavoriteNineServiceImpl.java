package jp.co.bungeejump.tokuban.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MPrefectureDao;
import jp.co.bungeejump.tokuban.dao.real.TNineDao;
import jp.co.bungeejump.tokuban.dao.real.VMerchDao;
import jp.co.bungeejump.tokuban.entity.real.MPrefecture;
import jp.co.bungeejump.tokuban.entity.real.TNine;
import jp.co.bungeejump.tokuban.entity.real.VMerch;
/**
 *
 * @author 末永
 * @version 0.2.0
 *
 */
@Service
public class FavoriteNineServiceImpl implements FavoriteNineService {

	@Autowired
	TNineDao tNineDao;

	@Autowired
	VMerchDao vMerchDao;

	@Autowired
	MPrefectureDao mPreDao;


	@Override
	public void addFavorite(Integer userId, Integer merchId) {
		VMerch vMerch = vMerchDao.findById(merchId).get();
		List<MPrefecture> preList = mPreDao.findByPrefectureName(vMerch.getPrefectureName());
		Integer regionId = preList.get(0).getRegionId();
		switch(regionId) {
		case 1:
			tNineDao.updateTNineHokkaido(merchId, userId);
			break;
		case 2:
			tNineDao.updateTNineTohoku(merchId, userId);
			break;
		case 3:
			tNineDao.updateTNineKanto(merchId, userId);
			break;
		case 4:
			tNineDao.updateTNineChubu(merchId, userId);
			break;
		case 5:
			tNineDao.updateTNineKinki(merchId, userId);
			break;
		case 6:
			tNineDao.updateTNineChugoku(merchId, userId);
			break;
		case 7:
			tNineDao.updateTNineShikoku(merchId, userId);
			break;
		case 8:
			tNineDao.updateTNineKyushu(merchId, userId);
			break;
		default:
			System.out.println("だいすきナインに追加しようとしましたが、想定外の不具合が発生しました");
			break;
		}
	}


	//だいすきナインを取得するメソッド
	@Override
	public VMerch[] findById(Integer userId) {
		VMerch[] favNine = new VMerch[9];

		List<TNine> tNineList = tNineDao.findByUserId(userId);

		//検索結果がなかったらコンソールに何か出してtNineのレコードを追加
		if (tNineList.size() == 0) {
			System.out.println(userId + "番のユーザーのだいすきナインのデータがありません！");
			TNine tNine = new TNine();
			tNine.setUserId(userId);
			tNineDao.save(tNine);
		} else {
			TNine tNine = tNineList.get(0);
			VMerch nullMerch = new VMerch();
			nullMerch.setMerchId(0);
			nullMerch.setMerchName("空席");
			if (tNine.getFavCenterId() != null) {
				favNine[0] = vMerchDao.getById(tNine.getFavCenterId());
			} else {
				favNine[0] = nullMerch;
			}
			if (tNine.getFavHokkaidoId() != null) {
				favNine[1] = vMerchDao.getById(tNine.getFavHokkaidoId());
			} else {
				favNine[1] = nullMerch;
			}
			if (tNine.getFavTohokuId() != null) {
				favNine[2] = vMerchDao.getById(tNine.getFavTohokuId());
			} else {
				favNine[2] = nullMerch;
			}
			if (tNine.getFavKantoId() != null) {
				favNine[3] = vMerchDao.getById(tNine.getFavKantoId());
			} else {
				favNine[3] = nullMerch;
			}
			if (tNine.getFavChubuId() != null) {
				favNine[4] = vMerchDao.getById(tNine.getFavChubuId());
			} else {
				favNine[4] = nullMerch;
			}
			if (tNine.getFavKinkiId() != null) {
				favNine[5] = vMerchDao.getById(tNine.getFavKinkiId());
			} else {
				favNine[5] = nullMerch;
			}
			if (tNine.getFavChugokuId() != null) {
				favNine[6] = vMerchDao.getById(tNine.getFavChugokuId());
			} else {
				favNine[6] = nullMerch;
			}
			if (tNine.getFavShikokuId() != null) {
				favNine[7] = vMerchDao.getById(tNine.getFavShikokuId());
			} else {
				favNine[7] = nullMerch;
			}
			if (tNine.getFavKyushuId() != null) {
				favNine[8] = vMerchDao.getById(tNine.getFavKyushuId());
			} else {
				favNine[8] = nullMerch;
			}
		}

		return favNine;
	}

	//指定のカラムを除去するメソッド
	@Override
	public void deleteFavorite(Integer userId, String column) {
		switch(column) {
		case "fav_center_id":
			tNineDao.updateTNineCenter(null, userId);
			break;
		case "fav_hokkaido_id":
			tNineDao.updateTNineHokkaido(null, userId);
			break;
		case "fav_tohoku_id":
			tNineDao.updateTNineTohoku(null, userId);
			break;
		case "fav_kanto_id":
			tNineDao.updateTNineKanto(null, userId);
			break;
		case "fav_chubu_id":
			tNineDao.updateTNineChubu(null, userId);
			break;
		case "fav_kinki_id":
			tNineDao.updateTNineKinki(null, userId);
			break;
		case "fav_chugoku_id":
			tNineDao.updateTNineChugoku(null, userId);
			break;
		case "fav_shikoku_id":
			tNineDao.updateTNineShikoku(null, userId);
			break;
		case "fav_kyushu_id":
			tNineDao.updateTNineKyushu(null, userId);
			break;
		default:
			System.out.println("だいすきナインから削除しようとしましたが、カラム名が間違っています");
			break;
		}

	}


	@Override
	public void selectAsCenter(Integer userId, Integer merchId) {
		tNineDao.updateTNineCenter(merchId, userId);
	}

}
