package jp.co.opt.edu03;

import java.util.ArrayList;
import java.util.List;

import jp.co.opt.edu01.Store;

public class StoreManager extends jp.co.opt.edu02.StoreManager {
	public StoreManager(List<Store> stores) {
		super(stores);
	}

	/**
	 * 当クラスで管理する店舗リスト(マスター)と引数で渡された店舗リスト(トランザクション)を店舗エリアで比較し、
	 * マッチしたがマスターの店舗をリストにして返す
	 * 
	 * @param transactionSortedStores
	 *            店舗エリアでソートされた店舗リスト(トランザクション)
	 * @return マッチング結果の店舗リスト
	 */
	public List<Store> matchingByArea(List<Store> transactionSortedStores) {

		// マスター
		List<Store> masterSortedStores = sortByArea();

		// 結果リストには当クラスで管理する店舗リストの中からマッチングした店舗を抜き出して格納
		List<Store> result = new ArrayList<>();

		Store emptyStore = new Store();
		emptyStore.setArea("");
		emptyStore.setName("");
		emptyStore.setSales(0);

		for (int i = 0; i < transactionSortedStores.size(); i++) {
			if (i < masterSortedStores.size()) {
				
				List<Store> emptyList = new ArrayList<>();
				emptyList.add(emptyStore);

				String areaMast = masterSortedStores.get(i).getArea();
				String areaTran = transactionSortedStores.get(i).getArea();

				if (areaMast == areaTran) {
					result.add(masterSortedStores.get(i));

					emptyList.addAll(transactionSortedStores);
					transactionSortedStores = emptyList;
				} else if (areaTran.compareTo(areaMast) < 0) {
					emptyList.addAll(masterSortedStores);
					masterSortedStores = emptyList;
				} else if (areaTran.compareTo(areaMast) > 0) {
					emptyList.addAll(transactionSortedStores);
					transactionSortedStores = emptyList;
				}

			}
		}

		/*
		 * TODO: 以下ルールに則り、当クラスで管理している店舗リストと引数の店舗リストをマッチングする ルール1.
		 * Mapやそのサブクラスを使用しない ルール2. forやwhileといったループを使用して良いのは１度のみ ルール3.
		 * すべての処理はこのメソッド内で完結させる(他の独自メソッド呼び出しNG)
		 */
		return result;
	}
}
