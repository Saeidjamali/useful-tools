package uk.ac.mmu.cnt.JSON;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class StudentList {
	
	@SerializedName("student")
	List<Student> sList  = new ArrayList<Student>();

	public List<Student> getsList() {
		return sList;
	}

	public void setsList(List<Student> sList) {
		this.sList = sList;
	}	

}