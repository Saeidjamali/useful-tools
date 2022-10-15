package uk.ac.mmu;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Datalist {

	@SerializedName("logdata")
	private List<Sensor> sList = new ArrayList<Sensor>();
	private List<Integer> time = new ArrayList<Integer>();
    private List<Double> temp = new ArrayList<Double>();
    private  List<Double> light = new ArrayList<Double>();
    private List<String> datetime = new ArrayList<String>();
    private List<Double> vcc = new ArrayList<Double>();
    private List<String> date = new ArrayList<String>();
    private List<Integer> mtime = new ArrayList<Integer>();
    private List<Double> maxph = new ArrayList<Double>();
    private List<Double> minph = new ArrayList<Double>();
    private List<String> Mdate = new ArrayList<String>();
    private List<Double> Averageph = new ArrayList<Double>();
    private List<Sensor> statics = new ArrayList<Sensor>();
    
   

	public List<Sensor> getStatics() {
		return statics;
	}

	public void setStatics(List<Sensor> statics) {
		this.statics = statics;
	}

	public List<Double> getAverageph() {
		return Averageph;
	}

	public void setAverageph(List<Double> averageph) {
		Averageph = averageph;
	}

	public List<String> getMdate() {
		return Mdate;
	}

	public void setMdate(List<String> mdate) {
		Mdate = mdate;
	}

	public List<Double> getMinph() {
		return minph;
	}

	public void setMinph(List<Double> minph) {
		this.minph = minph;
	}

	public List<Double> getMaxph() {
		return maxph;
	}

	public void setMaxph(List<Double> maxph) {
		this.maxph = maxph;
	}

	public List<Integer> getMtime() {
		return mtime;
	}

	public void setMtime(List<Integer> mtime) {
		this.mtime = mtime;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public List<Double> getVcc() {
		return vcc;
	}

	public void setVcc(List<Double> vcc) {
		this.vcc = vcc;
	}

	public List<String> getDatetime() {
		return datetime;
	}

	public void setDatetime(List<String> datetime) {
		this.datetime = datetime;
	}

	public List<Double> getLight() {
		return light;
	}

	public void setLight(List<Double> light) {
		this.light = light;
	}

	public List<Double> getTemp() {
		return temp;
	}

	public void setTemp(List<Double> temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "Datalist [time=" + time +"]";
	}

	public List<Integer> getTime() {
		return time;
	}

	public void setTime(List<Integer> time) {
		this.time = time;
	}

	public List<Sensor> getsList() {
		return sList;
	}

	public void setsList(List<Sensor> sList) {
		this.sList = sList;
	}
	

}
