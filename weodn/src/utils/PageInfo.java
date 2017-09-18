package utils;

import java.util.List;

public class PageInfo<T> {
	private static ThreadLocal<Integer> totalLocal = new ThreadLocal<>();
	private static ThreadLocal<Integer> pageLocal = new ThreadLocal<>();
	private static ThreadLocal<Integer> pageSizeLocal = new ThreadLocal<>();

	/**
	 * 当前页的数据
	 */
	private List<T> list;
	/**
	 * 总记录数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 第一页
	 */
	private int firstPage;
	/**
	 * 最后一页
	 */
	private int lastPage;
	/**
	 * 下一页
	 */
	private int nextPage;
	/**
	 * 上一页
	 */
	private int prePage;

	public PageInfo(List<T> list) {
		super();
		this.list = list;
		this.total = totalLocal.get();
		this.pageSize = pageSizeLocal.get();
		this.page = pageLocal.get();
		this.firstPage = 1;
		this.totalPage = this.total / this.pageSize + (this.totalPage % this.pageSize == 0 ? 0 : 1);
		this.lastPage = this.totalPage;
		this.nextPage = this.page >= this.lastPage ? this.lastPage : this.page + 1;
		this.prePage = this.page <= this.firstPage ? this.firstPage : this.page - 1;
	}

	public boolean isNextPage() {
		return this.page < this.lastPage;
	}

	public boolean isPrePage() {
		return this.page > this.firstPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	@Override
	public String toString() {
		return "PageInfo [list=" + list + ", total=" + total + ", totalPage=" + totalPage + ", page=" + page
				+ ", pageSize=" + pageSize + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", nextPage="
				+ nextPage + ", prePage=" + prePage + "]";
	}

}
