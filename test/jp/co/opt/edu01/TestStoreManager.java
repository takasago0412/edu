package jp.co.opt.edu01;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestStoreManager {

	/**
	 * 店舗エリア昇順ソートのテスト
	 */
	@Test
	public void testSortByArea() {
		StoreManager storeManager = new StoreManager(generateSampleData());
		List<Store> sortByArea = storeManager.sortByArea();
		
		// 期待されるソート順の店舗リスト
		List<Store> expect = generateSampleData();
		Collections.sort(expect, new StoreAreaComparator());

		for (int i = 0; i < expect.size(); i++) {
			// リストのソート順をチェック
			if (i < sortByArea.size()) {
				assertEquals(expect.get(i).getName(), sortByArea.get(i).getName());
			} else {
				Assert.fail("リストの数が一致しない");
			}
		}
	}

	/**
	 * 店舗売上高降順ソートのテスト
	 */
	@Test
	public void testSortBySales() {
		StoreManager storeManager = new StoreManager(generateSampleData());
		List<Store> sortBySales = storeManager.sortBySales();
		
		// 期待されるソート順の店舗リスト
		List<Store> expect = generateSampleData();
		Collections.sort(expect, new StoreSalesComparator());
		
		for (int i = 0; i < expect.size(); i++) {
			// リストのソート順をチェック
			if (i < sortBySales.size()) {
				assertEquals(expect.get(i).getName(), sortBySales.get(i).getName());
			} else {
				Assert.fail("リストの数が一致しない");
			}
		}
	}
	
	/**
	 * 店舗エリアで比較(昇順)
	 * 
	 * @author h.itoh
	 */
	public class StoreAreaComparator implements Comparator<Store> {
		@Override
		public int compare(Store o1, Store o2) {
			return o1.getArea().compareTo(o2.getArea());
		}
	}

	/**
	 * 売上高で比較(降順)
	 * 
	 * @author h.itoh
	 */
	public class StoreSalesComparator implements Comparator<Store> {
		@Override
		public int compare(Store o1, Store o2) {
			return o1.getSales() - o2.getSales();
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
