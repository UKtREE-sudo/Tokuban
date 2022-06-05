package jp.co.bungeejump.tokuban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.bungeejump.tokuban.service.GrandprixService;

/**
 * グランプリ、ラッキー特産品、キャンペーン
 * @author 小野
 *
 */
@Controller
public class BannerController {
	//キャンペーンは今回はwipにforwardしてください。
	@Autowired
	GrandprixService service;

	@RequestMapping(value = "/grandprix")
	public String grandprix() {
		/*
		regionId = 1;
		List<VMerch> topTen = service.getTopTen(regionId);
		model.addAttribute("topTen", topTen);
		*/
		return "grandprix";
	}

	@RequestMapping(value = "/fortune")
	public String fortune(){
		return "fortuneSpecialty";
	}

}
