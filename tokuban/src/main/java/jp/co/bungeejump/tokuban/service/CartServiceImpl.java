package jp.co.bungeejump.tokuban.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bungeejump.tokuban.dao.real.TCartDao;
import jp.co.bungeejump.tokuban.dao.real.VMerchDao;
import jp.co.bungeejump.tokuban.entity.real.TCart;
import jp.co.bungeejump.tokuban.entity.real.VMerch;
import jp.co.bungeejump.tokuban.form.CartForm;

/**
 * カート関係の処理をやるService
 * @author 末永
 * @version 0.2.0
 */

@Service
public class CartServiceImpl implements CartService {
	private static final double CONSUMPTION_TAX_FOOD = 1.08;
	private static final double CONSUMPTION_TAX_ELSE = 1.1;

	@Autowired
	TCartDao tcDao;
	@Autowired
	VMerchDao vmDao;

	@Override
	public void addToCart(Integer userId, CartForm form) {
		//TCartのインスタンス生成
		TCart tCart = new TCart();
		tCart.setUserId(userId);
		tCart.setMerchId(form.getMerchId());
		tCart.setQuantity(form.getQuantity());

		//daoのメソッドを呼び出す。
		tcDao.save(tCart);

	}


	@Override
	public List<CartForm> seeCart(Integer userId) {
		//ユーザの全カート情報取得
		List<TCart> tCartlist = tcDao.findByUserId(userId);

		//戻り値のリストを作成
		List<CartForm> cartFormList = new ArrayList<>();

		//繰り返しデータを取得し、Mapに入れる
		for(TCart tCart: tCartlist) {
			CartForm form = new CartForm();
			form.setMerchId(tCart.getMerchId());
			form.setQuantity(tCart.getQuantity());
			VMerch vMerch = vmDao.getById(tCart.getMerchId());
			form.setPrefectureName(vMerch.getPrefectureName());
			form.setMakerName(vMerch.getMakerName());
			form.setMerchName(vMerch.getMerchName());
			double includingTax;
			if (tCart.getMerchId() % 3 == 1) {
				//食品：軽減税率
				includingTax = vMerch.getBasePrice() * CONSUMPTION_TAX_FOOD;
			} else {
				includingTax = vMerch.getBasePrice() * CONSUMPTION_TAX_ELSE;
			}
			form.setIncludingTax((int)includingTax);
			cartFormList.add(form);
		}

		return cartFormList;
	}

	@Override
	public List<CartForm> deleteFromCart(Integer userId, Integer merchId) {
		tcDao.deleteByUserIdAndMerchId(userId, merchId);

		return seeCart(userId);
	}

	@Transactional
	@Override
	public void deleteAllFromCart(Integer userId) {
		tcDao.deleteByUserId(userId);
	}

	// 決済ボタン押したらその時点で入力されている個数をカートテーブルに反映させるやり方、
		// 全く思いつかなかったので頑張ってください。
		// thymeleaf使ってなんかやればいける…？

}
