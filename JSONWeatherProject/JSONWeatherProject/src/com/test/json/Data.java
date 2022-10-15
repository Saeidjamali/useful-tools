package com.test.json;

public class Data {
	
	private String year;
	private String month;
	private String tempmax;
	private String tempmin;
	private String af;
	private String rainmm;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getTempmax() {
		return tempmax;
	}
	public void setTempmax(String tempmax) {
		this.tempmax = tempmax;
	}
	public String getTempmin() {
		return tempmin;
	}
	public void setTempmin(String tempmin) {
		this.tempmin = tempmin;
	}
	public String getAf() {
		return af;
	}
	public void setAf(String af) {
		this.af = af;
	}
	public String getRainmm() {
		return rainmm;
	}
	public void setRainmm(String rainmm) {
		this.rainmm = rainmm;
	}
	@Override
	public String toString() {
		return "Data [year=" + year + ", month=" + month + ", tempmax="
				+ tempmax + ", tempmin=" + tempmin + ", af=" + af + ", rainmm="
				+ rainmm + "]";
	}	

}