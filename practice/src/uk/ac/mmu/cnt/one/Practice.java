package uk.ac.mmu.cnt.one;

final class MonthlyRecord 
{
	
	private final int year;
	private final int month;
	private final double tmax;
	private final double tmin;
	private final int afDays; 
	private final double rain;
	private final double sun;
	
	
	
	
	
	
	public MonthlyRecord(String record) {
		String[] fields = record.split("\\s+");
		
		
		this.year = Integer.valueOf(fields[0]);
		this.month = Integer.valueOf(fields[1]);
		this.tmax = Double.valueOf(fields[2]);
		this.tmin = Double.valueOf(fields[3]);
		this.afDays = Integer.valueOf(fields[4]);
		this.rain = Double.valueOf(fields[5]);
		this.sun = Double.valueOf(fields[6]);
		
		
	}
	
	
	public int getYear(){
		return year;
	}
	public int getMonth(){
		return month;
	}
	public double getTmax(){
		return tmax;
	}
	public double getTmin(){
		return tmin;
	}
	public int getafDays(){
		return afDays;
	}
	public double getRain(){
		return rain;
	}
	public double getSun(){
		return sun;
	}
	
	
	public String toString() {
		return new String ("year"+year+
				"month"+month+
				"tmax"+tmax+
				"tmin"+tmin+
				"afDays"+afDays+
				"rain"+rain+
				"sun"+sun);
		

	}
}