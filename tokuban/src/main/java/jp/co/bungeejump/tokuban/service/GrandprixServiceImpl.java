package jp.co.bungeejump.tokuban.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.MRegionDao;
import jp.co.bungeejump.tokuban.dao.real.VMerchDao;
import jp.co.bungeejump.tokuban.entity.real.MRegion;
import jp.co.bungeejump.tokuban.entity.real.VMerch;

/**
 * グランプリのことをやるServiceの実装クラス
 * @author 小野
 * @version 0.2.0
 */
@Service
public class GrandprixServiceImpl implements GrandprixService {

	@Autowired
	private MRegionDao mRegionDao;
	private VMerchDao vMerchDao;

	@Override
	public List<VMerch> getTopThree(Integer regionId) {
		//地方マスターの全情報を取得
		MRegion mRegion = mRegionDao.getById(regionId);

		//一位から三位の商品Idをそれぞれ変数に格納
		Integer first = mRegion.getFirstMerchId();
		Integer second = mRegion.getSecondMerchId();
		Integer third = mRegion.getThirdMerchId();

		//VMerchでそれぞれのIDごとに探してもらう
		List<VMerch> topThree = new ArrayList<VMerch>();
		topThree.add(vMerchDao.getById(first));
		topThree.add(vMerchDao.getById(second));
		topThree.add(vMerchDao.getById(third));

		return topThree;
	}

	@Override
	public List<VMerch> getTopTen(Integer regionId) {
		//地方マスターの全情報を取得
		MRegion mRegion = mRegionDao.getById(regionId);

		//一位から十位の商品Idをそれぞれ変数に格納
		Integer first = mRegion.getFirstMerchId();
		Integer second = mRegion.getSecondMerchId();
		Integer third = mRegion.getThirdMerchId();
		Integer fourth = mRegion.getFourthMerchId();
		Integer fifth = mRegion.getFifthMerchId();
		Integer sixth = mRegion.getSixthMerchId();
		Integer seventh = mRegion.getSeventhMerchId();
		Integer eighth = mRegion.getEighthMerchId();
		Integer ninth = mRegion.getNinthMerchId();
		Integer tenth = mRegion.getTenthMerchId();

		//VMerchでそれぞれのIDごとに探してもらう
		List<VMerch> topTen = new ArrayList<VMerch>();
		topTen.add(vMerchDao.getById(first));
		topTen.add(vMerchDao.getById(second));
		topTen.add(vMerchDao.getById(third));
		topTen.add(vMerchDao.getById(fourth));
		topTen.add(vMerchDao.getById(fifth));
		topTen.add(vMerchDao.getById(sixth));
		topTen.add(vMerchDao.getById(seventh));
		topTen.add(vMerchDao.getById(eighth));
		topTen.add(vMerchDao.getById(ninth));
		topTen.add(vMerchDao.getById(tenth));
		return topTen;
	}

}
