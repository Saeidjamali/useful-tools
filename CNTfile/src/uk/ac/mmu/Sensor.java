package uk.ac.mmu;

/*Sesnor class contains all of the variables needed to set the theirs values using setters
 * and use their values in other classes using getters.
*/
public class Sensor {
	
	
	
	private String millis;
	private String stamp;
	private String datetime;
	private String light;
	private String temp;
	private String vcc;
	
	private String date;
	private String time;
	private int hour;
	private int min;
	private double maxph;
	private double minph;
	private double averageph;
	private int mtime;
	private String zeroValue;
	
	private String staticmin;
	private String staticmax;
	private String staticav;

	public String getStaticmin() {
		return staticmin;
	}
	public void setStaticmin(String staticmin) {
		this.staticmin = staticmin;
	}
	public String getStaticmax() {
		return staticmax;
	}
	public void setStaticmax(String staticmax) {
		this.staticmax = staticmax;
	}
	public String getStaticav() {
		return staticav;
	}
	public void setStaticav(String staticav) {
		this.staticav = staticav;
	}
	public double getAverageph() {
		return averageph;
	}
	public void setAverageph(double averageph) {
		this.averageph = averageph;
	}
	public String getZeroValue() {
		return zeroValue;
	}
	public void setZeroValue(String zeroValue) {
		this.zeroValue = zeroValue;
	}
	public int getMtime() {
		return mtime;
	}
	public void setMtime(int mtime) {
		this.mtime = mtime;
	}
	public double getMinph() {
		return minph;
	}
	public void setMinph(double minph) {
		this.minph = minph;
	}
	public double getMaxph() {
		return maxph;
	}
	public void setMaxph(double maxph) {
		this.maxph = maxph;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMillis() {
		return millis;
	}
	public void setMillis(String string) {
		this.millis = string;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getLight() {
		return light;
	}
	public void setLight(String string) {
		this.light = string;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String string) {
		this.temp = string;
	}
	public String getVcc() {
		return vcc;
	}
	public void setVcc(String string) {
		this.vcc = string;
	}
	@Override
	public String toString() {
		return "Sensor [millis=" + millis + ", stamp=" + stamp + ", datetime=" + datetime + ", light=" + light
				+ ", temp=" + temp + ", vcc=" + vcc + "]";
	}

	
}
