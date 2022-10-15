package uk.ac.mmu;
//all of the statistics and their corresponding setter/getter and tostring 
public class Statistics {

	double AveragepHour;
    double MinpHour;
    double MaxpHour;
    int hour;
    String datetime;
    double hbeat;
    double temp;
    double hum;
    
    public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
    
	
	public double getHbeat() {
		return hbeat;
	}
	public void setHbeat(double hbeat) {
		this.hbeat = hbeat;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getHum() {
		return hum;
	}
	public void setHum(double hum) {
		this.hum = hum;
	}
	public double getMaxpHour() {
		return MaxpHour;
	}
	public void setMaxpHour(double maxpHour) {
		MaxpHour = maxpHour;
	}
	public double getAveragepHour() {
		return AveragepHour;
	}
	public void setAveragepHour(double averagepHour) {
		AveragepHour = averagepHour;
	}
	public double getMinpHour() {
		return MinpHour;
	}
	public void setMinpHour(double minpHour) {
		MinpHour = minpHour;
	}
	@Override
	public String toString() {
		return   datetime + "," + hbeat + "," + temp + "," + hum ;
	}
	

}
