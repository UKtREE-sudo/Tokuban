package jp.co.bungeejump.tokuban.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.bungeejump.tokuban.entity.real.MUser;
import jp.co.bungeejump.tokuban.entity.real.MemberDetails;
import jp.co.bungeejump.tokuban.form.InfoMForm;
import jp.co.bungeejump.tokuban.form.SearchForm;
import jp.co.bungeejump.tokuban.service.InfoMService;
import jp.co.bungeejump.tokuban.service.MapService;
import jp.co.bungeejump.tokuban.service.SettlementService;

/**
 * ログインとかログアウトとか退会とかその辺
 * @author 周東/ 小河原
 * @version 0.3.0
 */
@SessionAttributes({"achievementLevel", "coupons", "searchForm"})
@Controller
public class AuthController {

	@Autowired
	InfoMService infoMService;
	@Autowired
	MapService mapService;
	@Autowired
	SettlementService settlementService;
	@Autowired
	SearchForm searchForm;

	@ModelAttribute
	public SearchForm getSearchForm() {
		return searchForm;
	}

	/**
	 * ログイン画面を表示する
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/**
	 * ログアウトに成功後、トップ画面に遷移する
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logoutSuccess")
	public String dontSubmit(Model model) {
		model.addAttribute("authMessage", "ログアウトしました");
		return "top";
	}

	/**
	 * ログインに成功後、トップ画面に遷移する
	 * @param mDetails
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess(@AuthenticationPrincipal MemberDetails mDetails, Model model) {
		MUser loginUser = infoMService.findById(mDetails.getUserId());

		//実績レベルを取得してセッションへ
		model.addAttribute("achievementLevel", mapService.getAchievementLevel(mDetails.getUserId()));

		//クーポン枚数を取得してセッションへ
		Integer coupons = mapService.getAchievedCoupons(mDetails.getUserId()) - settlementService.getUsedCoupons(mDetails.getUserId());
		model.addAttribute("coupons", coupons);

		//表示名を取得して表示
		model.addAttribute("authMessage", loginUser.getNickname() + "さん、おかえりなさい！");
		return "top";
	}

	/**
	 * ログインに失敗、再度ログイン画面を表示する
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginFail")
	public String loginFail(Model model) {
		model.addAttribute("loginError", "メールアドレスかパスワードが間違っています");
		return "login";
	}

	/**
	 * 新規会員登録画面を表示する
	 * @return
	 */
	@RequestMapping(value = "/register")
	public String register(Model model) {
		InfoMForm infoMForm = new InfoMForm();
		model.addAttribute("infoMForm", infoMForm);
		return "register";
	}

	@RequestMapping(value = "/registerCheck")
	public String registerCheck(Model model, InfoMForm infoMForm, HttpServletRequest request) {
		String move = "register";
		ArrayList<String> registerError = new ArrayList<>();
		//メールアドレスが入力されている　かつ　メールアドレスが使用済みでないことをチェック
		if(infoMForm.getMailAddress().length() == 0) {
			registerError.add("メールアドレスは入力必須です");
		} else if(infoMService.isUsableMailAddress(infoMForm.getMailAddress()) == false) {
			registerError.add("そのメールアドレスはすでに使用されています");
		}
		//パスワードが2つとも入力されている　かつ　入力された2つのパスワードが一致していることをチェック
		if (infoMForm.getPassword1().length() == 0 || infoMForm.getPassword2().length() == 0) {
			registerError.add("パスワードは入力必須です");
		} else if (infoMForm.getPassword1().equals(infoMForm.getPassword2()) == false) {
			registerError.add("入力されたパスワードが一致しません");
		}
		//表示名の字数をチェック
		if (infoMForm.getNickname().length() < 1 || infoMForm.getNickname().length() > 8) {
			registerError.add("表示名は1文字以上8文字以下で設定してください");
		}
		//生年月日をチェック
		if (infoMService.isValidBirthday(infoMForm.getBirthYear(), infoMForm.getBirthMonth(), infoMForm.getBirthDate()) == false) {
			registerError.add("生年月日を正しく設定してください。");
		}

		//ここまでのチェックを全部パスしたらDBに登録してログイン
		if (registerError.size() == 0) {
			infoMService.register(infoMForm);
			try {
				request.login(infoMForm.getMailAddress(), infoMForm.getPassword1());
				move = "redirect:/loginSuccess";
			} catch (Exception e) {
				model.addAttribute("loginError", "想定外のエラーが発生しました。手動でログインしてください。");
				move = "login";
			}

		} else {
			model.addAttribute("registerError", registerError);
		}

		return move;
	}

	@RequestMapping(value = "/resignCheck")
	public String resignCheck() {
		return "resign";
	}

	@RequestMapping(value = "/resign")
	public String resign(@AuthenticationPrincipal MemberDetails mDetails, Model model, HttpServletRequest request){
		infoMService.resign(mDetails.getUserId());
		String message;
		try {
			request.logout();
			message = "退会しました";
		} catch (Exception e) {
			message = "ログアウトに失敗しました。お手数ですが、手動でログアウトしてください。";
		}
		model.addAttribute("authMessage", message);
		return "/top";
	}

	@RequestMapping(value = "/upgrade")
	public String update() {
		return "upgrade";
	}
	@RequestMapping(value = "/bePremium")
	public String bePremium(@AuthenticationPrincipal MemberDetails mDetails, Model model) {
		infoMService.bePremium(mDetails.getUserId());
		return "settlement";
	}


}

































