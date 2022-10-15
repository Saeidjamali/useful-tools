package uk.ac.mmu.cnt;





 class MonthlyRecord {
	
	//private variables as we don't want them to be accessed from outside of this class
	 // these are objects of MonthlyRecord class
	private final int year;
    private final int month;
    private double tmax=0;
    private double tmin=0;
    private int afDays=0; 
    private double rain=0;
    private double sun=0;
    
    // this this the constructor
public MonthlyRecord(String records) {
    	
    	//string that split the data by one or more white space. regular expression
    	String[] fields = records.split("\\s+");
    	
    	// go through a for loop in array and replace all of the unwanted characters such as("*" and ""#")
    	//regular expression meaning all non digits except for "." for decimal and "-" for negative numbers
    	for(int i=0; i<fields.length; i++){
    		fields[i]= fields[i].replaceAll("[^\\d.-]", "");
    	}
    	//convert String into integer and double in their own field
    	this.year = Integer.valueOf(fields[0]);
        this.month = Integer.valueOf(fields[1]);
        // this if statement filters "---" sign which is very common in text files
        if(!"---".equals(fields[2]))
        {
        this.tmax = Double.valueOf(fields[2]);
         }
        if(!"---".equals(fields[3]))
        {
        this.tmin = Double.valueOf(fields[3]);
        }
        if(!"---".equals(fields[4]))
        {
        this.afDays = Integer.valueOf(fields[4]);
        }
        if(!"---".equals(fields[5]))
        {
        this.rain = Double.valueOf(fields[5]);
        }
        if(!"---".equals(fields[6]))
        {
        this.sun = Double.valueOf(fields[6]);
        }
    	
    }

//getter. with this method  the values of each object can be used
public int getYear(){
	return this.year;
}
public int getMonth(){
	return this.month;
}
public double getTmax(){
	return this.tmax;
}
public double getTmin(){
	return this.tmin;
}
public int getafDays(){
	return this.afDays;
}
public double getRain(){
	return this.rain;
}
public double getSun(){
	return this.sun;
}

//toString method can be used the actual value of objects if printed
	public String toString() {
		return new String ("year"+this.year+
				"month"+this.month+
				"tmax"+this.tmax+
				"tmin"+this.tmin+
				"afDays"+this.afDays+
				"rain"+this.rain+
				"sun"+this.sun);
    	}
    
	}
