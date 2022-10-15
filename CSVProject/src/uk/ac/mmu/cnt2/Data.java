package uk.ac.mmu.cnt2;

public class Data {
	
	//define the attributes for your data here
	
	private int year;
	private int month;
	private double tempmax;
	private double tempmin;
	private double af;
	private double rainmm;	
	private double sunhour;
	
	public Data() {
		//empty constructor
	}

	//constructor
	public Data(int year, int month, double tempmax, double tempmin, double af,
			double rainmm, double sunhour) {
		this.year = year;
		this.month = month;
		this.tempmax = tempmax;
		this.tempmin = tempmin;
		this.af = af;
		this.rainmm = rainmm;
		this.sunhour = sunhour;
	}

	public double getSunhour() {
		return sunhour;
	}

	public void setSunhour(double sunhour) {
		this.sunhour = sunhour;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getTempmax() {
		return tempmax;
	}
	public void setTempmax(double tempmax) {
		this.tempmax = tempmax;
	}
	public double getTempmin() {
		return tempmin;
	}
	public void setTempmin(double tempmin) {
		this.tempmin = tempmin;
	}
	public double getAf() {
		return af;
	}
	public void setAf(double af) {
		this.af = af;
	}
	public double getRainmm() {
		return rainmm;
	}
	public void setRainmm(double rainmm) {
		this.rainmm = rainmm;
	}

	@Override
	public String toString() {
		return "Data [year=" + year + ", month=" + month + ", tempmax="
				+ tempmax + ", tempmin=" + tempmin + ", af=" + af + ", rainmm="
				+ rainmm + ", sunhour=" + sunhour + "]";
	}
}
