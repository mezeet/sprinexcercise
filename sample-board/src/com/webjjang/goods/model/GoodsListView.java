package com.webjjang.goods.model;

import java.util.List;

public class GoodsListView {
	
	public GoodsListView(){}
	public GoodsListView(int totalRow, int totalPage,
			int startRow, int endRow, int startPage,
			int endPage, int page,
			int rowPerPage, int pagePerGroup,
			List<Goods> list, String searchKey, String searchWord){
		// 생성하면선 값을 셋팅 : 미리 값을 구해 놓는다.
		this.totalRow = totalRow;
		this.totalPage = totalPage;
		this.startRow=startRow;
		this.endRow = endRow;
		this.startPage = startPage;
		this.endPage = endPage;
		this.page = page;
		this.rowPerPage = rowPerPage;
		this.pagePerGroup = pagePerGroup;
		this.list = list;
		this.searchKey = searchKey;
		this.searchWord = searchWord;
	}
	
	private int totalRow, totalPage, startRow,endRow,
	startPage, endPage, page ,rowPerPage, pagePerGroup;
	private List<Goods> list;
	private String searchKey, searchWord;
	
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	public int getPagePerGroup() {
		return pagePerGroup;
	}
	public void setPagePerGroup(int pagePerGroup) {
		this.pagePerGroup = pagePerGroup;
	}
	public List<Goods> getList() {
		return list;
	}
	public void setList(List<Goods> list) {
		this.list = list;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}
