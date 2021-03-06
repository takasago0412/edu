package jp.co.opt.edu01;

import java.util.List;

public class StoreManager {
	protected List<Store> stores;

	public StoreManager(List<Store> stores) {
		this.stores = stores;
	}

	/**
	 * 店舗エリアの昇順でソートした店舗リストを返却する
	 * @return ソート済み店舗リスト
	 */
	public List<Store> sortByArea() {
		//TODO: ソート処理を実装		

		for (int i = stores.size() - 1; i > 0 ; i--) {
			for (int j = 0; j < i; j++) {
				if (stores.get(j).getArea().compareTo(stores.get(j + 1).getArea()) > 0) {
					Store temp = stores.get(j);
					stores.set(j, stores.get(j + 1));
					stores.set(j + 1, temp);
				}
			}
		}

		return this.stores;
	}

	/**
	 * 売上高の降順でソートした店舗リストを返却する
	 * @return ソート済み店舗リスト
	 */
	public List<Store> sortBySales() {
		//TODO: ソート処理を実装
		for (int i = stores.size() - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (stores.get(j).getSales() > stores.get(j + 1).getSales()) {
					Store temp = stores.get(j);
					stores.set(j, stores.get(j + 1));
					stores.set(j + 1, temp);
				}
			}
		}
		return this.stores;
	}
}

/*
 * 【ソート方法】 
 * 隣り合う要素を交換し、大小関係が逆なら交換するソート方法で実装する。
 * 
 * 今回は配列の先頭から最後尾に向けて比較していくアルゴリズムで
 * 昇順の場合であれば、大きい値を最後尾に持っていく動きとなり
 * 降順の場合であれば、小さい値を最後尾に持っていく動きとなる。
 * 
 * 以下、昇順にする場合の例を示す。
 * (ソート前の配列[4, 2, 3, 1]) 
 * 
 * ・１順目の比較①:4と2を比較=>交換する
 *   配列[4, 2, 3, 1]
 *       ^  ^
 *       　↓
 *   配列[2, 4, 3, 1]
 *             
 * ・１順目の比較②:4と3を比較=>交換する
 *   配列[2, 4, 3, 1]
 *          ^  ^
 *          　↓
 *   配列[2, 3, 4, 1]
 * 
 * ・１順目の比較③:4と1を比較=>交換する
 *   配列[2, 3, 4, 1]
 *             ^  ^
 *             　↓
 *   配列[2, 3, 1, 4] 
 *   ※配列の最後に到達したため、4は最大値として確定(この後の比較対象外)
 * 
 * ・２順目の比較①:2と3を比較=>交換しない
 *   配列[2, 3, 1, 4]
 *       ^  ^
 * 
 * ・２順目の比較②:3と1を比較=>交換する
 *   配列[2, 3, 1, 4]
 *          ^  ^
 *          　↓
 *   配列[2, 1, 3, 4]
 *   ※3も確定(この後の比較対象外)
 * 
 * ・３順目の比較①:2と1を比較=>交換する
 *   配列[2, 1, 3, 4]
 *       ^  ^
 *       　↓
 *   配列[1, 2, 3, 4]
 *   ※３順目は先頭と先頭＋１の比較のみだったので、ソート完了と判断する
 * 
 */