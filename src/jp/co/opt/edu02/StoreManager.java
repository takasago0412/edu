package jp.co.opt.edu02;

import java.util.List;

import jp.co.opt.edu01.Store;

public class StoreManager extends jp.co.opt.edu01.StoreManager{
	public StoreManager(List<Store> stores){
		super(stores);
	}
	
	/**
	 * 店舗エリアごとに売上高を合計した店舗リストを返却する
	 * @return 店舗エリアごとの売上高合計済み店舗リスト
	 */
	public List<Store> groupByArea() {
		sortByArea();
		//TODO: 店舗エリアごとの売上高合計処理を実装(Mapやそのサブクラスを使用せずソートされた店舗リストを集計)
		return stores;
	}
}
