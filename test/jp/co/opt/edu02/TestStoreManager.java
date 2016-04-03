package jp.co.opt.edu02;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.co.opt.edu01.Store;

import org.junit.Assert;
import org.junit.Test;

public class TestStoreManager {

	/**
	 * 店舗エリアごとの売上高合計処理のテスト
	 */
	@Test
	public void testGroupByArea() {
		StoreManager storeManager = new StoreManager(generateSampleData());
		List<Store> groupByArea = storeManager.groupByArea();

		Map<String, Store> map = new HashMap<>();

		// 期待される売上高集計後の店舗リスト
		for (Store store : generateSampleData()) {
			if (!map.containsKey(store.getArea())) {
				map.put(store.getArea(), new Store(store.getArea(), store.getName(), store.getSales()));
			} else {
				Store work = map.get(store.getArea());
				work.setSales(work.getSales() + store.getSales());
			}
		}

		for (Store store : groupByArea) {
			String area = store.getArea();
			Store expect = map.get(area);

			if (expect != null) {
				assertEquals(expect.getSales(), store.getSales());
			} else {
				Assert.fail("想定されるキーが異なる");
			}
		}
	}

	/**
	 * サンプルデータ作成
	 * 
	 * @return 店舗データのリスト
	 */
	public static List<Store> generateSampleData() {
		List<Store> stores = new ArrayList<>();
		stores.add(new Store("鳥取県米子市", "安倍店", 54752));
		stores.add(new Store("鳥取県米子市", "花園店", 33338));
		stores.add(new Store("島根県松江市", "乃木店", 59012));
		stores.add(new Store("島根県松江市", "揖屋店", 5792));
		stores.add(new Store("鳥取県米子市", "皆生店", 87239));
		stores.add(new Store("鳥取県西伯郡", "伯耆店", 83924));
		stores.add(new Store("島根県安来市", "プラーナ店", 88151));
		stores.add(new Store("島根県出雲市", "塩冶店", 72344));
		stores.add(new Store("島根県松江市", "キャスパル店", 27594));
		stores.add(new Store("島根県出雲市", "大社店", 26481));
		stores.add(new Store("島根県松江市", "山代店", 55575));
		stores.add(new Store("島根県出雲市", "平田店", 73020));
		stores.add(new Store("島根県安来市", "広瀬店", 72027));
		stores.add(new Store("鳥取県米子市", "昭和町店", 60848));
		return stores;
	}
}
