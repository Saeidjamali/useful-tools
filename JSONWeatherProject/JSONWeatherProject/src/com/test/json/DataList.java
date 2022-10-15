package com.test.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DataList {
	
	@SerializedName("weather")
	List<Data> sList  = new ArrayList<Data>();

	public List<Data> getsList() {
		return sList;
	}

	public void setsList(List<Data> sList) {
		this.sList = sList;
	}
}