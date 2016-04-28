package jp.co.opt.edu03;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import jp.co.opt.edu01.Store;

import org.junit.Assert;
import org.junit.Test;

public class TestStoreManager {

	/**
	 * マッチング処理のテスト
	 */
	@Test
	public void testMatchingByArea1() {
		String[] areas = { "鳥取県米子市", "島根県出雲市", "島根県松江市", "島根県安来市", "鳥取県西伯郡" };

		for (int i = 0; i < areas.length; i++) {
			List<Store> transactionStores = new ArrayList<>();
			transactionStores.add(new Store(areas[i], "", 0));
			checkMatchingList(transactionStores);
		}
	}

	/**
	 * マッチング処理のテスト
	 */
	@Test
	public void testMatchingByArea2() {
		List<Store> transactionStores = new ArrayList<>();
		checkMatchingList(transactionStores);
	}

	/**
	 * マッチング処理のテスト
	 */
	@Test
	public void testMatchingByArea3() {
		List<Store> transactionStores = new ArrayList<>();
		transactionStores.add(new Store("東京都品川区", "", 0));
		checkMatchingList(transactionStores);
	}

	/**
	 * マッチング処理のテスト
	 */
	@Test
	public void testMatchingByArea4() {
		List<Store> transactionStores = new ArrayList<>();
		transactionStores.add(new Store("島根県松江市", "", 0));
		transactionStores.add(new Store("鳥取県西伯郡", "", 0));
		checkMatchingList(transactionStores);
	}

	/**
	 * マッチング処理のテスト
	 */
	@Test
	public void testMatchingByArea5() {
		List<Store> transactionStores = new ArrayList<>();
		transactionStores.add(new Store("島根県松江市", "", 0));
		transactionStores.add(new Store("島根県松江市", "", 0));
		checkMatchingList(transactionStores);
	}
	
	private void checkMatchingList(List<Store> transactionStores) {
		// 期待される店舗リスト
		List<Store> expectList = new ArrayList<>();
		for (Store store : new StoreManager(generateSampleData()).sortByArea()) {
			for (Store transactionStore : transactionStores) {
				if (transactionStore.getArea().equals(store.getArea())) {
					expectList.add(store);
					break;
				}
			}
		}

		StoreManager storeManager = new StoreManager(generateSampleData());
		List<Store> matchingByArea = storeManager.matchingByArea(transactionStores);
		for (int i = 0; i < expectList.size(); i++) {
			Store expectStore = (Store) expectList.get(i);

			if (i < matchingByArea.size()) {
				Store actualStore = (Store) matchingByArea.get(i);
				assertEquals(
						expectStore.getArea() + ":" + expectStore.getSales(),
						actualStore.getArea() + ":" + actualStore.getSales());
			} else {
				Assert.fail(expectStore.getArea() + "が見つかりません");
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
