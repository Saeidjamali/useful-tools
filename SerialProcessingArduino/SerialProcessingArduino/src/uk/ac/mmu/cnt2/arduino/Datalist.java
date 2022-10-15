package uk.ac.mmu.cnt2.arduino;

import java.util.ArrayList;
import java.util.List;


public class Datalist {
	
	private List<Statistics> sList = new ArrayList<Statistics>();

	public List<Statistics> getsList() {
		return sList;
	}

	public void setsList(List<Statistics> sList) {
		this.sList = sList;
	}

}
