package jp.co.bungeejump.tokuban.form;

import org.springframework.stereotype.Component;

/**
 * SerchService用のform
 * @author 高根
 * @version 0.0.1
 */
@Component
public class SearchForm {

	private String inputText;

	private String category;

	private Integer merchId;

	private Integer regionId;

	private String howToSort;

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getMerchId() {
		return merchId;
	}

	public void setMerchId(Integer merchId) {
		this.merchId = merchId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getHowToSort() {
		return howToSort;
	}

	public void setHowToSort(String howToSort) {
		this.howToSort = howToSort;
	}



}
