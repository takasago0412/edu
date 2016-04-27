package jp.co.opt.edu02;

import java.util.ArrayList;
import java.util.List;

import jp.co.opt.edu01.Store;

public class StoreManager extends jp.co.opt.edu01.StoreManager {
	public StoreManager(List<Store> stores) {
		super(stores);
	}

	/**
	 * 店舗エリアごとに売上高を合計した店舗リストを返却する
	 * @return 店舗エリアごとの売上高合計済み店舗リスト
	 */
	public List<Store> groupByArea() {
		List<Store> sortStoreList = sortByArea();
		List<Store> groupByAreaStore = new ArrayList<Store>();
		int sum = 0;
		String area = "";
		Store setStore;

		for (int i = 0; i < sortStoreList.size(); i++) {
			if (i == 0) {
				sum = sortStoreList.get(0).getSales();
				area = sortStoreList.get(0).getArea();
			}
			else {

				if (area.equals(sortStoreList.get(i).getArea())) {
					sum = sum + sortStoreList.get(i).getSales();
				}
				else {
					setStore = new Store();
					setStore.setArea(area);
					setStore.setSales(sum);
					setStore.setName("test");
					groupByAreaStore.add(setStore);
					sum = sortStoreList.get(i).getSales();
					area = sortStoreList.get(i).getArea();
				}
			}
			if (i == sortStoreList.size() - 1) {
				setStore = new Store();
				setStore.setArea(area);
				setStore.setSales(sum);
				setStore.setName("test");
				groupByAreaStore.add(setStore);
			}
		}
		//TODO: 店舗エリアごとの売上高合計処理を実装(Mapやそのサブクラスを使用せずソートされた店舗リストを集計)
		return groupByAreaStore;
	}
}
