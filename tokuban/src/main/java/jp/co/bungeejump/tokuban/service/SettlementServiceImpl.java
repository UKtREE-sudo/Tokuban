package jp.co.bungeejump.tokuban.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.TPurchaseDao;
import jp.co.bungeejump.tokuban.dao.real.TSettlementDao;
import jp.co.bungeejump.tokuban.dao.real.VMerchDao;
import jp.co.bungeejump.tokuban.entity.real.TPurchase;
import jp.co.bungeejump.tokuban.entity.real.TSettlement;
import jp.co.bungeejump.tokuban.entity.real.VMerch;
import jp.co.bungeejump.tokuban.entity.virtual.PurchaseHistoryDisplay;
import jp.co.bungeejump.tokuban.form.CartForm;
import jp.co.bungeejump.tokuban.form.PurchaseHistoryForm;

/**
*
* @author 小河原
* @version 0.2.1
*
*/
@Service
public class SettlementServiceImpl implements SettlementService {
	private static final double CONSUMPTION_TAX_FOOD = 1.08;
	private static final double CONSUMPTION_TAX_ELSE = 1.1;


	@Autowired
	private VMerchDao vMDao;

	@Autowired
	private TPurchaseDao tPDao;

	@Autowired
	private TSettlementDao tSDao;

	/**
	 * from 末永
	 * 決済用にほしいサービス（？）
	 * ・決済トランザクションにデータを追加し、決済IDを取得する
	 * ・購入履歴トランザクションにデータを追加
	 */

	/**
	 * 都道府県名から都道府県IDを調べるためのフィールド
	 */
	static final Map<String, Integer> PREFECTURE_DICT;
	static {
	    Map<String, Integer> map = new HashMap<>();
	    map.put("北海道", 1);
	    map.put("青森県", 2);
	    map.put("秋田県", 3);
	    map.put("岩手県", 4);
	    map.put("山形県", 5);
	    map.put("宮城県", 6);
	    map.put("福島県", 7);
	    map.put("茨城県", 8);
	    map.put("栃木県", 9);
	    map.put("群馬県", 10);
	    map.put("埼玉県", 11);
	    map.put("東京都", 12);
	    map.put("千葉県", 13);
	    map.put("神奈川県", 14);
	    map.put("新潟県", 15);
	    map.put("富山県", 16);
	    map.put("石川県", 17);
	    map.put("福井県", 18);
	    map.put("長野県", 19);
	    map.put("岐阜県", 20);
	    map.put("山梨県", 21);
	    map.put("静岡県", 22);
	    map.put("愛知県", 23);
	    map.put("滋賀県", 24);
	    map.put("京都府", 25);
	    map.put("大阪府", 26);
	    map.put("奈良県", 27);
	    map.put("三重県", 28);
	    map.put("和歌山県", 29);
	    map.put("兵庫県", 30);
	    map.put("鳥取県", 31);
	    map.put("島根県", 32);
	    map.put("岡山県", 33);
	    map.put("広島県", 34);
	    map.put("山口県", 35);
	    map.put("香川県", 36);
	    map.put("徳島県", 37);
	    map.put("愛媛県", 38);
	    map.put("高知県", 39);
	    map.put("福岡県", 40);
	    map.put("佐賀県", 41);
	    map.put("長崎県", 42);
	    map.put("熊本県", 43);
	    map.put("大分県", 44);
	    map.put("鹿児島県", 45);
	    map.put("宮崎県", 46);
	    map.put("沖縄県", 47);
	    PREFECTURE_DICT = Collections.unmodifiableMap(map);
	}


	@Override
	@Transactional
	public Integer makeSettlement(Integer userId, Integer addressId, Integer coupons) {
		TSettlement tSettlement = new TSettlement();
		tSettlement.setUserId(userId);
		tSettlement.setAddressId(addressId);
		tSettlement.setCoupons(coupons);
		TSettlement generatedEntity = tSDao.save(tSettlement);
		return generatedEntity.getSettlementId();
	};

	public void insertPurchaseHistory(List<CartForm> purchasedMerchList, Integer settlementId) {
		for (CartForm cartForm : purchasedMerchList) {
			TPurchase tPurchase = new TPurchase();
			tPurchase.setMerchId(cartForm.getMerchId());
			tPurchase.setQuantity(cartForm.getQuantity());
			tPurchase.setrStar(0);
			tPurchase.setSettlementId(settlementId);
			tPDao.save(tPurchase);
		}
	}


	@Override
	public String[] makeDate(int year, int month) {

		String mm = null;
		String[] date = new String[2];
		int endMonth = 0;
		int endYear = year;

		// monthをTIMESTAMPで使うためにString型に変換
		if (month == 0) {
			//月の指定がない場合は1月から検索
			mm = "01";
		}else if (month < 10) {
			mm = "" + 0 + month;
		} else {
			mm = "" + month;
		}

		String dateStart = year + "-" + mm + "-" + "01 00:00:00";

		// 翌月を定義
		if (month % 12 == 0) {
			//月の指定がない場合と12月の場合は翌年の1月までを検索
			endMonth = 1;
			endYear = year + 1;
		} else {
			endMonth = month + 1;
		}

		// endMonthをTIMESTAMPで使うためにString型に変換
		if (endMonth < 10) {
			mm = "" + 0 + month;
		} else {
			mm = "" + month;
		}

		String dateEnd = endYear + "-" + mm + "-" + "01 00:00:00";

		date[0] = dateStart;
		date[1] = dateEnd;

		return date;
	}

	@Override
	public Integer[] getMapPoint(Integer userId) {

		//MPを格納する配列を0で初期化
		Integer[] mapPoint = new Integer[48];
		for (int i = 1; i <= 47; i ++) {
			mapPoint[i] = 0;
		}

		//決済情報の一覧を取得
		//別に並べ替える必要はないけど新しくメソッド作る必要もない気がしたので。並べ替えない方が速いならメソッド作った方が良いかも。
		List<TSettlement> settlementList = tSDao.findByUserIdOrderBySettlementTimestampDesc(userId);

		//決済情報ごとに購入履歴情報を取得して金額を足していく
		if (settlementList.size() > 0) {
			for (TSettlement settlement : settlementList) {
				List<TPurchase> purchaseList = tPDao.findBySettlementIdAndMerchName(settlement.getSettlementId(), "%%");
				for (TPurchase purchase : purchaseList) {
					VMerch vMerch = vMDao.getById(purchase.getMerchId());
					mapPoint[PREFECTURE_DICT.get(vMerch.getPrefectureName())] += vMerch.getBasePrice();
				}
			}

		}

		return mapPoint;
	}


	@Override
	public List<PurchaseHistoryDisplay> showPurchaseHistory(Integer userId, PurchaseHistoryForm form) {

		List<PurchaseHistoryDisplay> purchaseHistoryList = new ArrayList<>();

		//決済情報の一覧を取得
		List<TSettlement> settlementList = new ArrayList<>();
		if (form.getYear() == 0) { //購入年月を指定しないで検索
			settlementList = tSDao.findByUserIdOrderBySettlementTimestampDesc(userId);

		} else { //購入年月を指定して検索
			String[] term = makeDate(form.getYear(), form.getMonth());
			settlementList = tSDao.findByDate(userId, term[0], term[1]);
		}

		//それぞれの決済IDに紐づく購入履歴情報を入力条件で絞り込んで検索
		if (settlementList != null) {
			if (form.getSelectOption() == 0) {
				//商品名で絞り込み検索
				for (TSettlement settlement : settlementList) {
					List<TPurchase> purchaseList = tPDao.findBySettlementIdAndMerchName(settlement.getSettlementId(), "%" + form.getInput() + "%");
					for (TPurchase purchase : purchaseList) {
						VMerch vMerch = vMDao.getById(purchase.getMerchId());
						PurchaseHistoryDisplay purchaseHD = new PurchaseHistoryDisplay();
						String timestamp = "" + settlement.getSettlementTimestamp();
						String[] date = timestamp.split(" ")[0].split("-");
						String[] time = timestamp.split(" ")[1].split(":");
						purchaseHD.setDate(date[0] + "年" + date[1] + "月" + date[2] + "日");
						purchaseHD.setTime(time[0] + ":" + time[1] + "注文");
						purchaseHD.setPurchasedQuantity(purchase.getQuantity());
						purchaseHD.setMerchId(vMerch.getMerchId());
						purchaseHD.setMerchName(vMerch.getMerchName());
						purchaseHD.setMakerName(vMerch.getMakerName());
						purchaseHD.setPrefectureName(vMerch.getPrefectureName());
						double includingTax;
						if (vMerch.getMerchId() % 3 == 1) {
							//食品：軽減税率
							includingTax = vMerch.getBasePrice() * CONSUMPTION_TAX_FOOD;
						} else {
							includingTax = vMerch.getBasePrice() * CONSUMPTION_TAX_ELSE;
						}
						purchaseHD.setIncludingTax((int)includingTax);
						purchaseHistoryList.add(purchaseHD);
					}
				}

			} else {
				//都道府県名で絞り込み検索
				for (TSettlement settlement : settlementList) {
					List<TPurchase> purchaseList = tPDao.findBySettlementIdAndPrefectureName(settlement.getSettlementId(), "%" + form.getInput() + "%");
					for (TPurchase purchase : purchaseList) {
						VMerch vMerch = vMDao.getById(purchase.getMerchId());
						PurchaseHistoryDisplay purchaseHD = new PurchaseHistoryDisplay();
						String timestamp = "" + settlement.getSettlementTimestamp();
						String[] date = timestamp.split(" ")[0].split("-");
						purchaseHD.setDate(date[0] + "年" + date[1] + "月" + date[2] + "日");
						purchaseHD.setTime(timestamp.split(" ")[1]);
						purchaseHD.setPurchasedQuantity(purchase.getQuantity());
						purchaseHD.setMerchId(vMerch.getMerchId());
						purchaseHD.setMerchName(vMerch.getMerchName());
						purchaseHD.setMakerName(vMerch.getMakerName());
						purchaseHD.setPrefectureName(vMerch.getPrefectureName());
						double includingTax;
						if (vMerch.getMerchId() % 3 == 1) {
							//食品：軽減税率
							includingTax = vMerch.getBasePrice() * CONSUMPTION_TAX_FOOD;
						} else {
							includingTax = vMerch.getBasePrice() * CONSUMPTION_TAX_ELSE;
						}
						purchaseHD.setIncludingTax((int)includingTax);
						purchaseHistoryList.add(purchaseHD);
					}
				}

			}

		}
		return purchaseHistoryList;
	}

	/**
	 *
	 */
	@Override
	public Integer getUsedCoupons(Integer userId) {
		Integer usedCoupons = 0;

		//決済情報の一覧を取得
		//別に並べ替える必要はないけど新しくメソッド作る必要もない気がしたので。並べ替えない方が速いならメソッド作った方が良いかも。
		List<TSettlement> settlementList = tSDao.findByUserIdOrderBySettlementTimestampDesc(userId);

		if (settlementList != null) {
			//決済情報ごとに使用したクーポン枚数を調べ、足していく
			for (TSettlement settlement : settlementList) {
				usedCoupons += settlement.getCoupons();
			}
		}
		return usedCoupons;
	}

}
