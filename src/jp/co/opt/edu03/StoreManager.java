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

		// 保持しているマスタを保存
		List<Store> masterStoreList = new ArrayList<>();
		List<Store> areaTransactionStoreList = new ArrayList<>();

		// 結果リストには当クラスで管理する店舗リストの中からマッチングした店舗を抜き出して格納
		List<Store> result = new ArrayList<>();
		List<String> resultArea = new ArrayList<>();

		// エリアごとのリストを格納
		List<List<Store>> listByArea = new ArrayList<List<Store>>();
		List<Store> storeListByArea = new ArrayList<Store>();
		String flg = "";

		boolean isFirst = true;

		String area = "";

		if (transactionSortedStores.size() > masterSortedStores.size()) {
			size = transactionSortedStores.size();
		} else {
			size = masterSortedStores.size();
		}

		for (int i = 0; i < size; i++) {
			try {
				// マスターから取得したエリアをmasterStoreに格納
				masterStoreList.add(masterSortedStores.get(i));

				if (!area.equals(masterSortedStores.get(i).getArea())) {
					if (isFirst == false) {
						listByArea.add(storeListByArea);
						flg = area;
						areaMaster.add(flg);
					}
					storeListByArea = new ArrayList<Store>();
					storeListByArea.add(masterSortedStores.get(i));
					isFirst = false;

				}

				// 直前のデータと一緒の場合、storeListByAreaにデータを格納
				else {
					storeListByArea.add(masterSortedStores.get(i));
				}

				area = masterSortedStores.get(i).getArea();

				// 最後のデータの場合
			    if(i == masterSortedStores.size()-1){
					listByArea.add(storeListByArea);
					if(!area.equals(flg)){
						if (areaTransaction.contains(flg) && !resultArea.contains(flg)) {
							result.addAll(listByArea.get(areaMaster.indexOf(flg)));
							resultArea.add(flg);
						}
					}
					flg = area;
					areaMaster.add(flg);
				}

				if (areaTransaction.contains(flg) && !resultArea.contains(flg)) {
					result.addAll(listByArea.get(areaMaster.indexOf(flg)));
					resultArea.add(flg);
				}

			} catch (Exception e) {
			}

			try {
				areaTransaction.add(transactionSortedStores.get(i).getArea());
				areaTransactionStoreList.add(transactionSortedStores.get(i));
				if (areaMaster.contains(areaTransactionStoreList.get(i).getArea()) && !resultArea.contains(areaTransactionStoreList.get(i).getArea())) {					
					result.addAll(listByArea.get(areaMaster.indexOf(areaTransactionStoreList.get(i).getArea())));
					resultArea.add(transactionSortedStores.get(i).getArea());
				}

			} catch (Exception e) {

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
