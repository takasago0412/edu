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

		// 比較する回数
        int size;

		// マスター
		List<Store> masterSortedStores = sortByArea();

		// 現在のエリアを比較する際に利用する。
		List<String> areaMaster = new ArrayList<>();
		List<String> areaTransaction = new ArrayList<>();

		// 結果リストには当クラスで管理する店舗リストの中からマッチングした店舗を抜き出して格納
		List<Store> result = new ArrayList<>();
		List<String> areaResult = new ArrayList<>();

		// エリアごとのリストを格納
		List<List<Store>> addToStore = new ArrayList<>();
		List<Store> storeList = new ArrayList<>();
		String areaToCompare = "";

		boolean isFirst = true;

		String area = "";

		if (transactionSortedStores.size() > masterSortedStores.size()) {
			size = transactionSortedStores.size();
		} else {
			size = masterSortedStores.size();
		}

		for (int i = 0; i < size; i++) {
			if(i < masterSortedStores.size()){
				if (!area.equals(masterSortedStores.get(i).getArea())) {
					if (isFirst == false) {
						addToStore.add(storeList);
						areaToCompare = area;
						areaMaster.add(areaToCompare);
					}
					storeList = new ArrayList<>();
					isFirst = false;

				}

				storeList.add(masterSortedStores.get(i));

				area = masterSortedStores.get(i).getArea();

				// 最後のデータの場合
				if (i == masterSortedStores.size() - 1) {
					addToStore.add(storeList);
					if(!area.equals(areaToCompare)){
						if (areaTransaction.contains(areaToCompare) && !areaResult.contains(areaToCompare)) {
							result.addAll(addToStore.get(areaMaster.indexOf(areaToCompare)));
							areaResult.add(areaToCompare);
						}
					}
					areaToCompare = area;
					areaMaster.add(areaToCompare);
				}

				if (areaTransaction.contains(areaToCompare) && !areaResult.contains(areaToCompare)) {
					result.addAll(addToStore.get(areaMaster.indexOf(areaToCompare)));
					areaResult.add(areaToCompare);
				}
			}


			if (i < transactionSortedStores.size()) {
				String areaTran = transactionSortedStores.get(i).getArea();
				areaTransaction.add(areaTran);
				if (areaMaster.contains(areaTran) && !areaResult.contains(areaTran)) {					
					result.addAll(addToStore.get(areaMaster.indexOf(areaTran)));
					areaResult.add(areaTran);
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
