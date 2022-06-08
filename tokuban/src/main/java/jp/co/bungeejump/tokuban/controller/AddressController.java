package jp.co.bungeejump.tokuban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.bungeejump.tokuban.entity.real.MemberDetails;
import jp.co.bungeejump.tokuban.entity.real.TAddress;
import jp.co.bungeejump.tokuban.form.AddressForm;
import jp.co.bungeejump.tokuban.service.AddressService;

/**
 * 住所関係
 * @author 小河原
 * @version 0.0.2
 */
@Controller
@RequestMapping("/mypage")
public class AddressController {
	
	@Autowired
	AddressService aService;
	
	@ModelAttribute
	public AddressForm aForm() {
		return new AddressForm();
	}
	
	/**
	 * 住所1情報表示 on 編集画面
	 * @param model
	 * @param userId
	 * @return
	 */
	@GetMapping("/showFormForUpdate1")
	public String showFormForUpdate1(Model model, 
			@AuthenticationPrincipal MemberDetails userId) {
		
		// 住所情報取得
		TAddress tAddress = aService.getAddress1(userId.getUserId());
		
		AddressForm aForm = new AddressForm();
		
		model.addAttribute("aform", aForm);
		
		// 住所情報表示
		model.addAttribute("addressInfo", tAddress);
		
		return "editAddress";
	}
	
	/**
	 * 住所2情報表示 on 編集画面
	 * @param model
	 * @param userId
	 * @return
	 */
		@GetMapping("/showFormForUpdate2")
		public String showFormForUpdate2(Model model, 
				@AuthenticationPrincipal MemberDetails userId) {
			
			// 住所情報取得
			TAddress tAddress = aService.getAddress2(userId.getUserId());
			
			AddressForm aForm = new AddressForm();
			
			model.addAttribute("aform", aForm);
			
			// 住所情報表示
			model.addAttribute("addressInfo", tAddress);
			
			return "editAddress2";
		}

	/**
	 * 住所1削除
	 * @param userId
	 * @return
	 */
	@Transactional
	@GetMapping("/delete1")
	public String deleteAddress1(@AuthenticationPrincipal MemberDetails userId) {
		
		aService.deleteAddress1(userId.getUserId());
		
		return "redirect:/mypage";
	}
	
	/**
	 * 住所2削除
	 * @param userId
	 * @return
	 */
	@Transactional
	@GetMapping("/delete2")
	public String deleteAddress2(@AuthenticationPrincipal MemberDetails userId) {
		
		aService.deleteAddress2(userId.getUserId());
		
		return "redirect:/mypage";
	}
	
	@RequestMapping(value="/addAddress")
	public String editAddress(Model model, 
			@AuthenticationPrincipal MemberDetails userId, 
			AddressForm form) {
		
		aService.editAddress1(form, userId.getUserId());
		
		return "redirect:/mypage";
	}
	
	@RequestMapping(value="/addAddress2")
	public String editAddress2(Model model, 
			@AuthenticationPrincipal MemberDetails userId, 
			AddressForm form) {
		
		aService.editAddress2(form, userId.getUserId());
		
		return "redirect:/mypage";
	}
}
