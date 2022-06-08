package jp.co.bungeejump.tokuban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.bungeejump.tokuban.entity.real.VMerch;
import jp.co.bungeejump.tokuban.entity.virtual.VMerchCategory;
import jp.co.bungeejump.tokuban.form.SearchForm;
import jp.co.bungeejump.tokuban.service.SearchServiceImpl;

/**
 * 検索と詳細表示
 * @author 高根
 * @version 0.0.3
 */
@Controller
public class SearchController {
	@Autowired
	private SearchServiceImpl searchService;

	//バナー検索
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchAll(SearchForm serchForm, Model model) {

		//検索結果表示
		try {
			List<VMerch> merchList = searchService.searchOnHeader(serchForm.getCategory(), serchForm.getInputText());
			model.addAttribute("merchList", merchList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//遷移先
		return "searchAll";
	}

	//ソート変更
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public String sort(SearchForm serchForm, Model model) {

		//検索結果のIDを取得しソートに並べ替えて送る
		List<VMerchCategory> merchList = searchService.sortMerch(serchForm.getHowToSort(), serchForm.getMerchId());
		model.addAttribute("merchList", merchList);

		//遷移先
		return "searchAll";

	}

	//指定された地方の商品をおすすめ度高い順に表示
	@RequestMapping(value = "/premium/searchFavorite", method = RequestMethod.POST)
	public String saerchRegion(SearchForm serchForm, Model model) {

		//regionIdの同じ商品を取得しおすすめ度順に並べて送る
		List<VMerch> merchList = searchService.searchByRegion(serchForm.getRegionId());
		model.addAttribute("merchList", merchList);

		//遷移先
		return "searchAll";

	}

}
