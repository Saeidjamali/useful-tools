/**
 * 
 */
/**
 * @author saeid
 *
 */
package uk.monthlyrecord;
final class MonthlyRecord  {

	
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
	
	
	public int year(){
		return year;
	}
	public int month(){
		return month;
	}
	public double tmax(){
		return tmax;
	}
	public double tmin(){
		return tmin;
	}
	public int afDays(){
		return afDays;
	}
	public double rain(){
		return rain;
	}
	public double sun(){
		return sun;
		
	}
}


