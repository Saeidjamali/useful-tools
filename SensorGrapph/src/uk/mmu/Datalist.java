package uk.mmu;

import java.util.ArrayList;
import java.util.List;


public class Datalist {
	
	private List<Statistics> sList = new ArrayList<Statistics>();
	private List<Double> hbeat = new ArrayList<Double>();
	private List<Double> temp = new ArrayList<Double>();
	private List<Double> hum = new ArrayList<Double>();
	private List<Integer> time = new ArrayList<Integer>();
	public List<Integer> getTime() {
		return time;
	}

	public void setTime(List<Integer> time) {
		this.time = time;
	}

	public List<Double> getHbeat() {
		return hbeat;
	}

	public void setHbeat(List<Double> hbeat) {
		this.hbeat = hbeat;
	}

	public List<Double> getTemp() {
		return temp;
	}

	public void setTemp(List<Double> temp) {
		this.temp = temp;
	}

	public List<Double> getHum() {
		return hum;
	}

	public void setHum(List<Double> hum) {
		this.hum = hum;
	}

	public List<Statistics> getsList() {
		return sList;
	}

	public void setsList(List<Statistics> sList) {
		this.sList = sList;
	}

}
