package com.guigu.mytime.locat.sortlist;

public class SortModel {
	private int id;
	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母

	public SortModel() {
	}

	public SortModel(String name, String sortLetters) {
		this.name = name;
		this.sortLetters = sortLetters;
	}

	public SortModel(int id, String name, String sortLetters) {
		this.id = id;
		this.name = name;
		this.sortLetters = sortLetters;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}


}
