package jp.co.bungeejump.tokuban.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.bungeejump.tokuban.entity.real.MemberDetails;
import jp.co.bungeejump.tokuban.entity.real.TAddress;
import jp.co.bungeejump.tokuban.entity.real.TCart;
import jp.co.bungeejump.tokuban.form.CartForm;
import jp.co.bungeejump.tokuban.form.SettlementForm;
import jp.co.bungeejump.tokuban.service.AddressService;
import jp.co.bungeejump.tokuban.service.CartService;
import jp.co.bungeejump.tokuban.service.MapService;
import jp.co.bungeejump.tokuban.service.SettlementService;

/**
 * カート、決済関係
 * @author 末永
 * @version 0.1.3
 *
 */
@SessionAttributes({"achievementLevel", "coupons", "tAddress"})
@Controller
public class PurchaseController {

	@Autowired
	CartService cService;

	@Autowired
	AddressService addressS;

	@Autowired
	SettlementService settlementS;

	@Autowired
	MapService mapService;

	@Autowired
	HttpSession session;

	@ModelAttribute
	public CartForm cartForm() {
		return new CartForm();
	}

	@ModelAttribute
	public TCart tcart() {
		return new TCart();
	}

	@ModelAttribute
	SettlementForm settlementForm() {
		return new SettlementForm();

	}

	@RequestMapping(value = "/setAddress1")
	public String setAddress1(@AuthenticationPrincipal MemberDetails memberDetails,Model model) {
		TAddress tAddress = addressS.getAddress1(memberDetails.getUserId());
		model.addAttribute("tAddress", tAddress);
		return "delivery";
	}

	@RequestMapping(value = "/setAddress2")
	public String setAddress2(@AuthenticationPrincipal MemberDetails memberDetails,Model model) {
		TAddress tAddress = addressS.getAddress2(memberDetails.getUserId());
		model.addAttribute("tAddress", tAddress);
		return "delivery";
	}

	@RequestMapping(value = "/selectAddress")
	public String selectAddress(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
		TAddress address1 = addressS.getAddress1(memberDetails.getUserId());
		TAddress address2 = addressS.getAddress2(memberDetails.getUserId());

		model.addAttribute("address1", address1);
		model.addAttribute("address2", address2);
		return "address";
	}

	// delivery.htmlに飛ぶ
	@RequestMapping(value = "/moveDelivery", method = RequestMethod.GET)
	public String moveDelivery(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
		//商品idと個数を取得（購入チェックボックスが入っているもののみ）
		//List<CartForm> results = cService.seeCart(memberDetails.getUserId());

		//		//合計値を計算or取得
		//		SettlementForm sForm = new SettlementForm();
		//		int sum = sForm.getSum();
		//		System.out.println(sum);
		//session
		TAddress tAddress = addressS.getAddress1(memberDetails.getUserId());
		model.addAttribute("tAddress", tAddress);

		return "delivery";
	}

	//deliveryから、couponへ行き、情報を送る。（合計金額は先に計算してmodelに入れたほうがいい？）
	@RequestMapping(value = "/selectCoupon", method = RequestMethod.GET)
	public String selectCoupon(@AuthenticationPrincipal MemberDetails memberDetails, Model model, @ModelAttribute("coupons")Integer coupons) {
		//配送先idを取得し、session
		//クーポン利用最大枚数を、model.attribute
		//合計金額をmodel.attribute()

		List<CartForm> cartList = cService.seeCart(memberDetails.getUserId());
		int sum = 0;
		for (CartForm cartForm : cartList) {
			sum += cartForm.getIncludingTax() * cartForm.getQuantity();
		}
		int max;
		if (coupons * 500 <= sum) {
			max = coupons;
		} else {
			max = sum / 500;
		}

		/*何やってるのか分からん
		List<VMerch> vmerchList = new ArrayList<>();
		Integer[] quantityArray = new Integer[cartList.size()];
		int i = 0;

		for (CartForm cart : cartList) {

			vmerchList.add(vmDao.getById(cart.getMerchId()));
			quantityArray[i] = cart.getQuantity();
			i++;
		}

		int sum = 0;

		i = 0;

		for (VMerch vm : vmerchList) {
			sum += vm.getBasePrice() * quantityArray[i];
			i++;
		}
		*/

		model.addAttribute("sum", sum);
		model.addAttribute("max", max);
		model.addAttribute("min", 0);

		return "coupon";
	}

	//couponから購入情報を受け取り、データベースをいっぱい更新して、settlementへ。
	@RequestMapping(value = "/settlement", method = RequestMethod.GET)
	public String settlement(@AuthenticationPrincipal MemberDetails memberDetails, Model model,
			@RequestParam(name = "couponUsing")Integer couponUsing, @ModelAttribute("tAddress")TAddress tAddress) {
		//クーポン枚数はrequestスコープでいける。商品情報は今回はチェックボックスないのでDBから取得。
		//配送先idはsessionスコープじゃないといけないのでsubmitするところでちゃんとやって。

		//決済Tに情報を追加し、決済IDを受け取る
		Integer settlementId = settlementS.makeSettlement(memberDetails.getUserId(), tAddress.getAddressId(), couponUsing);

		//購入履歴Tに情報を追加
		settlementS.insertPurchaseHistory(cService.seeCart(memberDetails.getUserId()), settlementId);


		//カートから情報を削除
		cService.deleteAllFromCart(memberDetails.getUserId());

		//実績レベルを取得してセッションへ
		model.addAttribute("achievementLevel", mapService.getAchievementLevel(memberDetails.getUserId()));

		//クーポン枚数を取得してセッションへ
		Integer coupons = mapService.getAchievedCoupons(memberDetails.getUserId()) - settlementS.getUsedCoupons(memberDetails.getUserId());
		model.addAttribute("coupons", coupons);


		return "settlement";
	}

	// カート表示（model.addAttribute("RESULT_MAP", resultMap)）
	@RequestMapping(value = "/seeCart", method = RequestMethod.GET)
	public String seeCart(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {

		//これカートから削除じゃなくて普通に表示でいいですよね
		List<CartForm> results = cService.seeCart(memberDetails.getUserId());

		//結果をmodelに保持
		model.addAttribute("RESULTS", results);

		//遷移前のURLを取得し、遷移
		return "cart";
	}

	//カートに追加（遷移元のURLを取得して処理後そこにforwardする）（多分）
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addCart(@AuthenticationPrincipal MemberDetails memberDetails, CartForm cartForm, Model model,
			HttpServletRequest request) {

		//セッションからユーザーIDを取得し、商品IDと数量をフォームから取得する。
		//データを追加
		cService.addToCart((Integer) memberDetails.getUserId(), cartForm);

		//遷移前のURLを取得し、遷移
		return "redirect:/seeCart";
	}

	//カートから削除（同上）
	@RequestMapping(value = "/deleteCart", method = RequestMethod.GET)
	public String deleteCart(@AuthenticationPrincipal MemberDetails memberDetails, CartForm cartForm, Model model,
			HttpServletRequest request, Integer merchId) {
		CartForm form = new CartForm();

		model.addAttribute("cartForm", form);

		//指定のカートを削除し、結果を取得
		List<CartForm> results = cService.deleteFromCart(memberDetails.getUserId(), merchId);

		//結果をmodelに保持
		model.addAttribute("RESULTS", results);

		//遷移前のURLを取得し、遷移
		return "redirect:/seeCart";
	}
}
