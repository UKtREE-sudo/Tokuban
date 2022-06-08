package jp.co.bungeejump.tokuban.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.bungeejump.tokuban.entity.real.MUser;
import jp.co.bungeejump.tokuban.entity.real.MemberDetails;
import jp.co.bungeejump.tokuban.entity.real.TAddress;
import jp.co.bungeejump.tokuban.entity.real.VMerch;
import jp.co.bungeejump.tokuban.entity.virtual.PurchaseHistoryDisplay;
import jp.co.bungeejump.tokuban.form.CartForm;
import jp.co.bungeejump.tokuban.form.InfoMForm;
import jp.co.bungeejump.tokuban.form.PurchaseHistoryForm;
import jp.co.bungeejump.tokuban.service.AddressService;
import jp.co.bungeejump.tokuban.service.FavoriteNineService;
import jp.co.bungeejump.tokuban.service.InfoMService;
import jp.co.bungeejump.tokuban.service.MapService;
import jp.co.bungeejump.tokuban.service.SettlementService;

/**
 * マイページ関連
 * @author 小河原(会員情報取得) / 周東
 * @version 0.3.0
 */
@SessionAttributes({"infoMForm", "achievementLevel"})
@Controller
public class MypageController {

	@Autowired
	private InfoMService infoMS;

	@Autowired
	private AddressService addressS;

	@Autowired
	private MapService mapS;

	@Autowired
	private FavoriteNineService favNineS;

	@Autowired
	private SettlementService settlementS;

	/*
	@ModelAttribute
	public PurchaseHistoryForm getPurchaseHistoryForm() {
		PurchaseHistoryForm purchaseHistoryForm = new PurchaseHistoryForm();
		return purchaseHistoryForm;
	}
	*/

	public MypageController(InfoMService infoMs) {
		this.infoMS = infoMs;
	}

	// 会員情報取得 ピュア・会員情報
	@RequestMapping(value = {"/mypage", "/mypage-infoM"})
	public String showUserInfo(Model model, @AuthenticationPrincipal MemberDetails loginUser) {

		/* 会員情報タブの中身用 */
		// ユーザーIDから会員情報を取得
		MUser mUser = infoMS.findById(loginUser.getUserId());

		InfoMForm infoMForm = new InfoMForm();
		infoMForm.setMailAddress(mUser.getMailAddress());
		infoMForm.setPassword1("");
		infoMForm.setPassword2("");
		infoMForm.setNickname(mUser.getNickname());
		String[] birthday = mUser.getBirthday().toString().split("-");
		infoMForm.setBirthYear(birthday[0]);
		infoMForm.setBirthMonth(birthday[1].replaceFirst("^0+", ""));
		infoMForm.setBirthDate(birthday[2].replaceFirst("^0+", ""));

		//roleだけはformにないので個別で
		boolean isPremium;
		if (mUser.getRole().equals("PAID")) {
			isPremium = true;
		} else {
			isPremium = false;
		}

		// 住所1取得
		TAddress tAddress1 = addressS.getAddress1(loginUser.getUserId());

		// 住所2取得
		TAddress tAddress2 = addressS.getAddress2(loginUser.getUserId());


		/* 全国マップタブの中身用 */
		// 都道府県レベル
		Integer[] prefLevels = mapS.getPLevel(loginUser.getUserId());
		String[] pLevel = new String[48];
		pLevel[0] = "";
		for (int i = 1; i <= 47; i ++) {
			pLevel[i] = "pLevel" + prefLevels[i];
		}

		//実績関連
		List<String[]> achievementBoard = mapS.getAchievementBoard(loginUser.getUserId());


		/* だいすきナインタブの中身用 */
		VMerch[] favNine = favNineS.findById(loginUser.getUserId());

		CartForm cartFormCenter = new CartForm();
		cartFormCenter.setMerchId(favNine[0].getMerchId());

		CartForm cartFormHokkaido = new CartForm();
		cartFormHokkaido.setMerchId(favNine[1].getMerchId());

		CartForm cartFormTohoku = new CartForm();
		cartFormTohoku.setMerchId(favNine[2].getMerchId());

		CartForm cartFormKanto = new CartForm();
		cartFormKanto.setMerchId(favNine[3].getMerchId());

		CartForm cartFormChubu = new CartForm();
		cartFormChubu.setMerchId(favNine[4].getMerchId());

		CartForm cartFormKinki = new CartForm();
		cartFormKinki.setMerchId(favNine[5].getMerchId());

		CartForm cartFormChugoku = new CartForm();
		cartFormChugoku.setMerchId(favNine[6].getMerchId());

		CartForm cartFormShikoku = new CartForm();
		cartFormShikoku.setMerchId(favNine[7].getMerchId());

		CartForm cartFormKyushu = new CartForm();
		cartFormHokkaido.setMerchId(favNine[8].getMerchId());

		/* 購入履歴タブの中身用 */
		PurchaseHistoryForm purchaseHistoryForm = new PurchaseHistoryForm();
		purchaseHistoryForm.setSelectOption(0);
		purchaseHistoryForm.setInput("");
		purchaseHistoryForm.setYear(0);
		purchaseHistoryForm.setMonth(0);
		List<PurchaseHistoryDisplay> purchaseHistoryDisplay = settlementS.showPurchaseHistory(loginUser.getUserId(), purchaseHistoryForm);


		//「会員情報」をチェック状態にする
		boolean[] isChecked = {false, true, false, false, false, false};


		// infoM.htmlにaddAttribute
		model.addAttribute("infoMForm", infoMForm);
		model.addAttribute("isPremium", isPremium);
		model.addAttribute("address1", tAddress1);
		model.addAttribute("address2", tAddress2);
		model.addAttribute("pLevel", pLevel);
		model.addAttribute("achievementBoard", achievementBoard);

		model.addAttribute("cartFormCenter", cartFormCenter);
		model.addAttribute("cartFormHokkaido", cartFormHokkaido);
		model.addAttribute("cartFormTohoku", cartFormTohoku);
		model.addAttribute("cartFormKanto", cartFormKanto);
		model.addAttribute("cartFormChubu", cartFormChubu);
		model.addAttribute("cartFormKinki", cartFormKinki);
		model.addAttribute("cartFormChugoku", cartFormChugoku);
		model.addAttribute("cartFormShikoku", cartFormShikoku);
		model.addAttribute("cartFormKyushu", cartFormKyushu);
		model.addAttribute("favNine", favNine);

		model.addAttribute("purchaseHistoryDisplay", purchaseHistoryDisplay);
		model.addAttribute("isChecked", isChecked);

		return "mypage";
	}

	// 会員情報編集
	@RequestMapping(value = "/editInfoM")
	public String editInfoM() {
		return "editInfoM";
	}

	//会員情報更新前チェック
	@RequestMapping(value = "/editCheck")
	public String editCheck(InfoMForm infoMForm, @AuthenticationPrincipal MemberDetails loginUser, Model model) {
		String move = "editInfoM";
		ArrayList<String> updateError = new ArrayList<>();
		//メールアドレスが使用済みでないことをチェック
		if(infoMS.isUsableMailAddress(infoMForm.getMailAddress(), loginUser.getUserId()) == false) {
			updateError.add("そのメールアドレスはすでに他のユーザーに使用されています");
		}
		//入力された2つのパスワードが一致していることをチェック
		if (infoMForm.getPassword1().equals(infoMForm.getPassword2()) == false) {
			updateError.add("入力されたパスワードが一致しません");
		}
		//表示名の字数をチェック
		if (infoMForm.getNickname().length() < 1 || infoMForm.getNickname().length() > 8) {
			updateError.add("表示名は1文字以上8文字以下で設定してください");
		}
		//生年月日をチェック
		if (infoMS.isValidBirthday(infoMForm.getBirthYear(), infoMForm.getBirthMonth(), infoMForm.getBirthDate()) == false) {
			updateError.add("生年月日を正しく設定してください。");
		}

		//ここまでのチェックを全部パスしたら確認画面へ
		if (updateError.size() == 0) {
			move = "editCheck";
		} else {
			model.addAttribute("updateError", updateError);
		}

		return move;
	}

	/**
	 * 会員情報を更新するメソッド
	 * @param infoMForm
	 * @param loginUser
	 * @return
	 */
	@RequestMapping(value = "/updateInfoM")
	public String updateInfoM(InfoMForm infoMForm, @AuthenticationPrincipal MemberDetails loginUser, Model model) {
		infoMS.editInfoM(loginUser.getUserId(), infoMForm);
		System.out.println(infoMForm.getMailAddress());
		System.out.println(infoMForm.getPassword1());
		model.addAttribute("infoMForm", null);
		return "redirect:/mypage";
	}

	// 全国マップ（コピペ）
	@RequestMapping(value = "/mypage-map")
	public String mypageMap(Model model, @AuthenticationPrincipal MemberDetails loginUser) {
		/* 会員情報タブの中身用 */
		// ユーザーIDから会員情報を取得
		MUser mUser = infoMS.findById(loginUser.getUserId());

		InfoMForm infoMForm = new InfoMForm();
		infoMForm.setMailAddress(mUser.getMailAddress());
		infoMForm.setPassword1("");
		infoMForm.setPassword2("");
		infoMForm.setNickname(mUser.getNickname());
		String[] birthday = mUser.getBirthday().toString().split("-");
		infoMForm.setBirthYear(birthday[0]);
		infoMForm.setBirthMonth(birthday[1].replaceFirst("^0+", ""));
		infoMForm.setBirthDate(birthday[2].replaceFirst("^0+", ""));

		//roleだけはformにないので個別で
		boolean isPremium;
		if (mUser.getRole().equals("PAID")) {
			isPremium = true;
		} else {
			isPremium = false;
		}

		// 住所1取得
		TAddress tAddress1 = addressS.getAddress1(loginUser.getUserId());

		// 住所2取得
		TAddress tAddress2 = addressS.getAddress2(loginUser.getUserId());


		/* 全国マップタブの中身用 */
		// 都道府県レベル
		Integer[] prefLevels = mapS.getPLevel(loginUser.getUserId());
		String[] pLevel = new String[48];
		pLevel[0] = "";
		for (int i = 1; i <= 47; i ++) {
			pLevel[i] = "pLevel" + prefLevels[i];
		}

		//実績関連
		List<String[]> achievementBoard = mapS.getAchievementBoard(loginUser.getUserId());


		/* だいすきナインタブの中身用 */
		VMerch[] favNine = favNineS.findById(loginUser.getUserId());

		CartForm cartFormCenter = new CartForm();
		cartFormCenter.setMerchId(favNine[0].getMerchId());

		CartForm cartFormHokkaido = new CartForm();
		cartFormHokkaido.setMerchId(favNine[1].getMerchId());

		CartForm cartFormTohoku = new CartForm();
		cartFormTohoku.setMerchId(favNine[2].getMerchId());

		CartForm cartFormKanto = new CartForm();
		cartFormKanto.setMerchId(favNine[3].getMerchId());

		CartForm cartFormChubu = new CartForm();
		cartFormChubu.setMerchId(favNine[4].getMerchId());

		CartForm cartFormKinki = new CartForm();
		cartFormKinki.setMerchId(favNine[5].getMerchId());

		CartForm cartFormChugoku = new CartForm();
		cartFormChugoku.setMerchId(favNine[6].getMerchId());

		CartForm cartFormShikoku = new CartForm();
		cartFormShikoku.setMerchId(favNine[7].getMerchId());

		CartForm cartFormKyushu = new CartForm();
		cartFormHokkaido.setMerchId(favNine[8].getMerchId());

		/* 購入履歴タブの中身用 */
		PurchaseHistoryForm purchaseHistoryForm = new PurchaseHistoryForm();
		purchaseHistoryForm.setSelectOption(0);
		purchaseHistoryForm.setInput("");
		purchaseHistoryForm.setYear(0);
		purchaseHistoryForm.setMonth(0);
		List<PurchaseHistoryDisplay> purchaseHistoryDisplay = settlementS.showPurchaseHistory(loginUser.getUserId(), purchaseHistoryForm);



		//「全国マップ」をチェック状態にする
		boolean[] isChecked = {false, false, true, false, false, false};

		// infoM.htmlにaddAttribute
		model.addAttribute("infoMForm", infoMForm);
		model.addAttribute("isPremium", isPremium);
		model.addAttribute("address1", tAddress1);
		model.addAttribute("address2", tAddress2);
		model.addAttribute("pLevel", pLevel);
		model.addAttribute("achievementBoard", achievementBoard);

		model.addAttribute("cartFormCenter", cartFormCenter);
		model.addAttribute("cartFormHokkaido", cartFormHokkaido);
		model.addAttribute("cartFormTohoku", cartFormTohoku);
		model.addAttribute("cartFormKanto", cartFormKanto);
		model.addAttribute("cartFormChubu", cartFormChubu);
		model.addAttribute("cartFormKinki", cartFormKinki);
		model.addAttribute("cartFormChugoku", cartFormChugoku);
		model.addAttribute("cartFormShikoku", cartFormShikoku);
		model.addAttribute("cartFormKyushu", cartFormKyushu);
		model.addAttribute("favNine", favNine);

		model.addAttribute("purchaseHistoryDisplay", purchaseHistoryDisplay);
		model.addAttribute("isChecked", isChecked);

		return "mypage";

	}

	// だいすきナイン（コピペ）
	@RequestMapping(value = "/mypage-favoriteNine")
	public String mypageFavoriteNine(Model model, @AuthenticationPrincipal MemberDetails loginUser) {
		/* 会員情報タブの中身用 */
		// ユーザーIDから会員情報を取得
		MUser mUser = infoMS.findById(loginUser.getUserId());

		InfoMForm infoMForm = new InfoMForm();
		infoMForm.setMailAddress(mUser.getMailAddress());
		infoMForm.setPassword1("");
		infoMForm.setPassword2("");
		infoMForm.setNickname(mUser.getNickname());
		String[] birthday = mUser.getBirthday().toString().split("-");
		infoMForm.setBirthYear(birthday[0]);
		infoMForm.setBirthMonth(birthday[1].replaceFirst("^0+", ""));
		infoMForm.setBirthDate(birthday[2].replaceFirst("^0+", ""));

		//roleだけはformにないので個別で
		boolean isPremium;
		if (mUser.getRole().equals("PAID")) {
			isPremium = true;
		} else {
			isPremium = false;
		}

		// 住所1取得
		TAddress tAddress1 = addressS.getAddress1(loginUser.getUserId());

		// 住所2取得
		TAddress tAddress2 = addressS.getAddress2(loginUser.getUserId());


		/* 全国マップタブの中身用 */
		// 都道府県レベル
		Integer[] prefLevels = mapS.getPLevel(loginUser.getUserId());
		String[] pLevel = new String[48];
		pLevel[0] = "";
		for (int i = 1; i <= 47; i ++) {
			pLevel[i] = "pLevel" + prefLevels[i];
		}

		//実績関連
		List<String[]> achievementBoard = mapS.getAchievementBoard(loginUser.getUserId());


		/* だいすきナインタブの中身用 */
		VMerch[] favNine = favNineS.findById(loginUser.getUserId());

		CartForm cartFormCenter = new CartForm();
		cartFormCenter.setMerchId(favNine[0].getMerchId());

		CartForm cartFormHokkaido = new CartForm();
		cartFormHokkaido.setMerchId(favNine[1].getMerchId());

		CartForm cartFormTohoku = new CartForm();
		cartFormTohoku.setMerchId(favNine[2].getMerchId());

		CartForm cartFormKanto = new CartForm();
		cartFormKanto.setMerchId(favNine[3].getMerchId());

		CartForm cartFormChubu = new CartForm();
		cartFormChubu.setMerchId(favNine[4].getMerchId());

		CartForm cartFormKinki = new CartForm();
		cartFormKinki.setMerchId(favNine[5].getMerchId());

		CartForm cartFormChugoku = new CartForm();
		cartFormChugoku.setMerchId(favNine[6].getMerchId());

		CartForm cartFormShikoku = new CartForm();
		cartFormShikoku.setMerchId(favNine[7].getMerchId());

		CartForm cartFormKyushu = new CartForm();
		cartFormHokkaido.setMerchId(favNine[8].getMerchId());

		/* 購入履歴タブの中身用 */
		PurchaseHistoryForm purchaseHistoryForm = new PurchaseHistoryForm();
		purchaseHistoryForm.setSelectOption(0);
		purchaseHistoryForm.setInput("");
		purchaseHistoryForm.setYear(0);
		purchaseHistoryForm.setMonth(0);
		List<PurchaseHistoryDisplay> purchaseHistoryDisplay = settlementS.showPurchaseHistory(loginUser.getUserId(), purchaseHistoryForm);



		//「だいすきナイン」をチェック状態にする
		boolean[] isChecked = {false, false, false, true, false, false};

		// infoM.htmlにaddAttribute
		model.addAttribute("infoMForm", infoMForm);
		model.addAttribute("isPremium", isPremium);
		model.addAttribute("address1", tAddress1);
		model.addAttribute("address2", tAddress2);
		model.addAttribute("pLevel", pLevel);
		model.addAttribute("achievementBoard", achievementBoard);

		model.addAttribute("cartFormCenter", cartFormCenter);
		model.addAttribute("cartFormHokkaido", cartFormHokkaido);
		model.addAttribute("cartFormTohoku", cartFormTohoku);
		model.addAttribute("cartFormKanto", cartFormKanto);
		model.addAttribute("cartFormChubu", cartFormChubu);
		model.addAttribute("cartFormKinki", cartFormKinki);
		model.addAttribute("cartFormChugoku", cartFormChugoku);
		model.addAttribute("cartFormShikoku", cartFormShikoku);
		model.addAttribute("cartFormKyushu", cartFormKyushu);
		model.addAttribute("favNine", favNine);

		model.addAttribute("purchaseHistoryDisplay", purchaseHistoryDisplay);
		model.addAttribute("isChecked", isChecked);

		return "mypage";
	}

	// 購入履歴（コピペ）
	@RequestMapping(value = "/mypage-purchaseHistory")
	public String mypagePurchaseHistory(Model model, @AuthenticationPrincipal MemberDetails loginUser) {
		/* 会員情報タブの中身用 */
		// ユーザーIDから会員情報を取得
		MUser mUser = infoMS.findById(loginUser.getUserId());

		InfoMForm infoMForm = new InfoMForm();
		infoMForm.setMailAddress(mUser.getMailAddress());
		infoMForm.setPassword1("");
		infoMForm.setPassword2("");
		infoMForm.setNickname(mUser.getNickname());
		String[] birthday = mUser.getBirthday().toString().split("-");
		infoMForm.setBirthYear(birthday[0]);
		infoMForm.setBirthMonth(birthday[1].replaceFirst("^0+", ""));
		infoMForm.setBirthDate(birthday[2].replaceFirst("^0+", ""));

		//roleだけはformにないので個別で
		boolean isPremium;
		if (mUser.getRole().equals("PAID")) {
			isPremium = true;
		} else {
			isPremium = false;
		}

		// 住所1取得
		TAddress tAddress1 = addressS.getAddress1(loginUser.getUserId());

		// 住所2取得
		TAddress tAddress2 = addressS.getAddress2(loginUser.getUserId());


		/* 全国マップタブの中身用 */
		// 都道府県レベル
		Integer[] prefLevels = mapS.getPLevel(loginUser.getUserId());
		String[] pLevel = new String[48];
		pLevel[0] = "";
		for (int i = 1; i <= 47; i ++) {
			pLevel[i] = "pLevel" + prefLevels[i];
		}

		//実績関連
		List<String[]> achievementBoard = mapS.getAchievementBoard(loginUser.getUserId());


		/* だいすきナインタブの中身用 */
		VMerch[] favNine = favNineS.findById(loginUser.getUserId());

		CartForm cartFormCenter = new CartForm();
		cartFormCenter.setMerchId(favNine[0].getMerchId());

		CartForm cartFormHokkaido = new CartForm();
		cartFormHokkaido.setMerchId(favNine[1].getMerchId());

		CartForm cartFormTohoku = new CartForm();
		cartFormTohoku.setMerchId(favNine[2].getMerchId());

		CartForm cartFormKanto = new CartForm();
		cartFormKanto.setMerchId(favNine[3].getMerchId());

		CartForm cartFormChubu = new CartForm();
		cartFormChubu.setMerchId(favNine[4].getMerchId());

		CartForm cartFormKinki = new CartForm();
		cartFormKinki.setMerchId(favNine[5].getMerchId());

		CartForm cartFormChugoku = new CartForm();
		cartFormChugoku.setMerchId(favNine[6].getMerchId());

		CartForm cartFormShikoku = new CartForm();
		cartFormShikoku.setMerchId(favNine[7].getMerchId());

		CartForm cartFormKyushu = new CartForm();
		cartFormHokkaido.setMerchId(favNine[8].getMerchId());

		/* 購入履歴タブの中身用 */
		PurchaseHistoryForm purchaseHistoryForm = new PurchaseHistoryForm();
		purchaseHistoryForm.setSelectOption(0);
		purchaseHistoryForm.setInput("");
		purchaseHistoryForm.setYear(0);
		purchaseHistoryForm.setMonth(0);
		List<PurchaseHistoryDisplay> purchaseHistoryDisplay = settlementS.showPurchaseHistory(loginUser.getUserId(), purchaseHistoryForm);

		//「だいすきナイン」をチェック状態にする
		boolean[] isChecked = {false, false, false, false, true, false};
		// infoM.htmlにaddAttribute
		model.addAttribute("infoMForm", infoMForm);
		model.addAttribute("isPremium", isPremium);
		model.addAttribute("address1", tAddress1);
		model.addAttribute("address2", tAddress2);
		model.addAttribute("pLevel", pLevel);
		model.addAttribute("achievementBoard", achievementBoard);

		model.addAttribute("cartFormCenter", cartFormCenter);
		model.addAttribute("cartFormHokkaido", cartFormHokkaido);
		model.addAttribute("cartFormTohoku", cartFormTohoku);
		model.addAttribute("cartFormKanto", cartFormKanto);
		model.addAttribute("cartFormChubu", cartFormChubu);
		model.addAttribute("cartFormKinki", cartFormKinki);
		model.addAttribute("cartFormChugoku", cartFormChugoku);
		model.addAttribute("cartFormShikoku", cartFormShikoku);
		model.addAttribute("cartFormKyushu", cartFormKyushu);
		model.addAttribute("favNine", favNine);

		model.addAttribute("purchaseHistoryDisplay", purchaseHistoryDisplay);
		model.addAttribute("isChecked", isChecked);

		return "mypage";

	}

}
