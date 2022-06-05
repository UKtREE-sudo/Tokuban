package jp.co.bungeejump.tokuban.service;

import java.util.List;

import jp.co.bungeejump.tokuban.entity.real.VMerch;
import jp.co.bungeejump.tokuban.entity.virtual.VMerchCategory;

/**
 * 検索関係のことをやるserviceインタフェース
 * <p>結構重めというか考えること多めのメソッドなので手詰まりになりかけたら早めにタスク投げてください。</p>
 * @author 高根
 */
public interface SearchService {

	/**
	 * ヘッダーから検索を行うメソッド
	 * <p>なんかのビュー（入力値を検索したいカラムに応じて考えて）にm_categoryを内部結合して検索かける感じ？<br>
	 * Dao作ってやったらいいと思うけど、多分クエリ書かなきゃいけなくなる気がする。<br>
	 * 検索結果はIDだけListに入れる感じで取得して、ループ回してVMerchDao呼んだらいいんじゃないかな。<br>
	 * おすすめ度取得するのもこのクラスでやるのが良い気がしてきた。他サービスで使わないしゆっくり実装していいよ。<br>
	 * ソートするのもあんまり実装思いついてない。VMerchがキー、おすすめ度がバリューのHashMapとか？</p>
	 * @param category フォームから取得
	 * @param inputText フォームから取得
	 * @return 検索結果一覧（おすすめ度高い順）
	 */
	public List<VMerch> searchOnHeader(String category, String inputText);

	/**
	 * 検索結果をソートするメソッド
	 * <p>ソートの方法はif文かswitch文書く感じか。プルダウンで選択するやつなので例外処理は考えなくて良さそう。<br>
	 * ソート対象の検索結果はどうやって取得するんだろう？　フィールド？　検索条件だけ保存してクエリ投げなおす？</p>
	 * @param howToSort フォームから取得
	 * @return 検索結果一覧（指定順）
	 */
	public List<VMerchCategory> sortMerch(String howToSort,Integer merchId);

	/**
	 * 指定された地方の商品をおすすめ度高い順に全件取得するメソッド
	 * <p>何となくこんな感じっていうのは末永さんがわかってると思う。</p>
	 * @param regionId だいすきナインの選抜するボタンに埋め込んであるはず。
	 * @return 検索結果一覧（おすすめ度高い順）
	 */
	public List<VMerch> searchByRegion(Integer regionId);

	/**
	 * 商品詳細を取得するメソッド
	 * <p>もう眠いのでEntityとかDaoも頑張って考えてほしい。寝て起きて元気になってたら相談乗るので…<br>
	 * いつも大変な仕事ばっかり押し付けて本当にすみません。</p>
	 * @return 商品詳細
	 */
	//public MerchDetail getMerchDetail(Integer merchId);
}
