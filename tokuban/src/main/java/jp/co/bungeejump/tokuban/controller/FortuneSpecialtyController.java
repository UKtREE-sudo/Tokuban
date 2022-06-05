package jp.co.bungeejump.tokuban.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.bungeejump.tokuban.entity.real.MQuestion;
import jp.co.bungeejump.tokuban.form.FortuneSpecialtyForm;
import jp.co.bungeejump.tokuban.service.FortuneSpecialtyService;

/**
 * ラッキー特産品
 * @author 小野
 *　@version 0.0.1
 */

@RestController
@SessionAttributes("ansewer")
public class FortuneSpecialtyController {

	@Autowired
	FortuneSpecialtyService service;

	@RequestMapping(value = "/premium/FortuneSpecialty")
	public String getQuestion(Date date, Model model) {

		List<MQuestion> question = service.getQuestion(date);
		model.addAttribute("questions", question);

		return "FortuneSpecialty";
	}

	@RequestMapping(value = "/premium/FortuneSpecialty", method = RequestMethod.POST)
	@ResponseBody
	public String getAnswer(@RequestBody FortuneSpecialtyForm form ) {
		System.out.println(form.getFirstOption());
		System.out.println(form.getSecondOption());
		return "FortuneSpecialty";

	}


}
