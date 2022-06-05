package jp.co.bungeejump.tokuban.form;

/**
 * 購入履歴検索フォーム
 * @author 小河原
 * @version 0.2.0
 */
public class PurchaseHistoryForm {

	/**
	 * 検索項目を検索, 商品名, 産地 の3項目を
	 * ↓
	 * 商品名 = 0, 産地 = 1 の2項目に変更
	 */
	private int selectOption;

	private String input;

	private int year;

	private int month;

	public int getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(int selectOption) {
		this.selectOption = selectOption;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}



























