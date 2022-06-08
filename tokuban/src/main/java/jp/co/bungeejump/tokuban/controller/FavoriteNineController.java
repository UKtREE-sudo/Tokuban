package jp.co.bungeejump.tokuban.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.bungeejump.tokuban.entity.real.MemberDetails;
import jp.co.bungeejump.tokuban.form.FavoriteNineForm;
import jp.co.bungeejump.tokuban.service.FavoriteNineService;

/**
 * だいすきナイン関係
 * @author 末永
 * @version 0.0.1
 *
 */
@Controller
public class FavoriteNineController {
	@Autowired
	FavoriteNineService fService;
	//地方代表にする
	
	@RequestMapping(value="/premium/addFavorite", method=RequestMethod.POST)
	public String addFavorite(@AuthenticationPrincipal MemberDetails memberDetails, FavoriteNineForm form, BindingResult result,
			Model model, HttpServletRequest request) {
		//入力された情報でデータ追加
		fService.addFavorite((Integer)memberDetails.getUserId(),form.getMerchId());
		//遷移前のURLを取得し、遷移
		return request.getHeader("REFERER").substring(30);
	}

	//センターにする
	@RequestMapping(value="/premium/addCenter", method=RequestMethod.POST)
	public String selectAsCenter(@AuthenticationPrincipal MemberDetails memberDetails, FavoriteNineForm form, BindingResult result,
			Model model, HttpServletRequest request) {
		//入力された情報でデータ追加
		fService.selectAsCenter((Integer)memberDetails.getUserId(),form.getMerchId());
		//遷移前のURLを取得し、遷移
		return request.getHeader("REFERER").substring(30);
	}

	//だいすきナイン削除する。
	@RequestMapping(value="/premium/deleteFavorite", method=RequestMethod.POST)
	public String deleteFavorite(@AuthenticationPrincipal MemberDetails memberDetails, FavoriteNineForm form, BindingResult result,
			Model model, HttpServletRequest request) {
		//入力された情報でデータ削除
		fService.deleteFavorite((Integer)memberDetails.getUserId(), form.getColumn());
		//遷移前のURLを取得し、遷移
		return request.getHeader("REFERER").substring(30);
	}

}
