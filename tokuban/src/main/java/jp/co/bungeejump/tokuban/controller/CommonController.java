package jp.co.bungeejump.tokuban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.bungeejump.tokuban.dao.real.VMerchDao;
import jp.co.bungeejump.tokuban.entity.real.MFood;
import jp.co.bungeejump.tokuban.entity.real.MLiquor;
import jp.co.bungeejump.tokuban.entity.real.VMerch;
import jp.co.bungeejump.tokuban.form.CartForm;
import jp.co.bungeejump.tokuban.service.MFoodService;
import jp.co.bungeejump.tokuban.service.MLiquorService;

/**
 * topとかwipとか汎用的なやつ。service要らん。
 * @author 小河原
 * @version 0.0.1
 */
@Controller
public class CommonController {

	@Autowired
	private MFoodService mfService;
	
	@Autowired
	private MLiquorService mlService;

	@Autowired
	private VMerchDao vmDao;

	@RequestMapping(value = { "/", "/top" })
	public String top() {
		return "top";
	}

	@RequestMapping(value = "/wip")
	public String wip() {
		return "wip";
	}

	@RequestMapping(value = "/merchDetail", method = RequestMethod.GET)
	public String merchDetail(Model model, Integer merchId) {

		CartForm form = new CartForm();

		model.addAttribute("cart", form);

		VMerch vMerch = vmDao.getById(merchId);

		model.addAttribute("detail", vMerch);

		if(merchId % 3 == 1) {
			MFood mFood = mfService.getById(merchId);

			model.addAttribute("merch", mFood);
		}else if(merchId % 3 == 2) {
			MLiquor mLiquor = mlService.getById(merchId);

			model.addAttribute("merch", mLiquor);
		}

		return "merchDetail";
	}
}
