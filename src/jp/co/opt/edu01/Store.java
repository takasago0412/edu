package jp.co.opt.edu01;

/**
 * 店舗
 * 
 * @author h.itoh
 */
public class Store {
	/**
	 * 店舗エリア名
	 */
	private String area;

	/**
	 * 店舗名
	 */
	private String name;

	/**
	 * 売上高
	 */
	private int sales;

	public Store(String area, String name, int sales) {
		this.area = area;
		this.name = name;
		this.sales = sales;
	}

	public Store() {
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return String.format("Area:%s, Name:%s, Sales:%d", area, name, sales);
	}
}
