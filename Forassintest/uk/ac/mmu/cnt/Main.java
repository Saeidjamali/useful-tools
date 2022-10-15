package uk.ac.mmu.cnt;
//Import from library
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
	//Global Arraylist. This can be used in any class. This ArrayList is a type of MonthlyRecord
	static List<MonthlyRecord>   records = new ArrayList<MonthlyRecord>();

	public static void main(String[] args) throws IOException {
		
		//Scanner to get the name of the file from user
		 Scanner scan = new Scanner(System.in);
			System.out.println("Enter a city please!");
			
			// variable filename is equal to user input
			String filename = scan.next();
			
			//open the file using BufferedReader and transfer user input to lower case
			// try and catch statement is used to deal with Exception 
		try{	BufferedReader file = new BufferedReader(new FileReader( filename.toLowerCase() + "data.txt"));
		
		    //variable for reading the opened file
			String line;
			
			//go through a loop while there is still more to read
			for(int i =0;(line = file.readLine()) != null; i++){
				
				//skip the header info
			    if(i>=8 ){	
				//trim the leading and trailing white space
					 line =line.trim();
					 
				//and add the data to out records
				 records.add(new MonthlyRecord(line));
			    }
			}
		        //Close the file and scanner
				file.close();
				scan.close();
				//run the Simple graph from its own class.
				new SimpleGraph();
				//Handle exception
		   } catch (FileNotFoundException e){
			   	System.out.println("Data not found. Please import a file or check the spelling!");
			   	System.out.println("");
			   	System.out.println("Format guidance:");
			   	System.out.println("-If you are still struglling check the following");
			   	System.out.println("-Make sure data files are in the root directory");
			   	System.out.println("-The file name must be the name of the city following by 'data.txt'");
			   	System.out.println("-year is a 4 digit integer, no decimal allowed");
			   	System.out.println("-Month is a 2 digit integer, no decimal allowed");
			   	System.out.println("-Af is an integer, no decimal allowed");
		   }		
		

			//declare some variable
			double RainAv =0;
			double TmaxAv=0;
			double TminAv =0;
			double SunAv=0;
			int AfdayAv=0;
			double sumRain=0;
			double sumTmax=0;
			double sumTmin=0;
			double sumSun=0;
			int sumAfday=0;
			int year = 0;
			int count = 0;
			double maxRain =0;
			double maxTmax =0;
			double  maxTmin=0;
			double maxSun=0;
			int maxAf=0;	
			//go through a loop while index is less than the size of ArrayList
			for(int i=0; i<records.size();i++)
			 {
				//Find the highest value in each column
				//if the value is greater than maxRain, assume maxRain is equal to the value and do it over and over again until there is no greater.
				if(records.get(i).getRain() > maxRain){
					maxRain = records.get(i).getRain();
				}
				else if(records.get(i).getTmax() >  maxTmax){
					maxTmax = records.get(i).getTmax();
					
				}
				else if(records.get(i).getTmin() >  maxTmin){
					maxTmin = records.get(i).getTmin();
				}
				else if (records.get(i).getSun() > maxSun){
					maxSun = records.get(i).getSun();
				}
				else if (records.get(i).getafDays() > maxAf){
					maxAf = records.get(i).getafDays();
				}
				//get the first value of year if it is not equal to variable year(originally equals to 0)execute the code below
				if(records.get(i).getYear() != year)
				{
					//for the first loop value of count is zero so if it is not zero execute the code below
					if(count != 0)
					{
						//if count is not zero that means it is maximum 12 before the year changes
						//by the time this code is executed the number of count is maximum 12
						RainAv = sumRain/count;
						TmaxAv = sumTmax/count;
						TminAv = sumTmin/count;
						SunAv = sumSun/count;
						AfdayAv= sumAfday/count;
						
						//print out everything
						System.out.println("Average of rain fall:" + Math.round(RainAv) + "mm");
						System.out.println("Max rain fall value "+maxRain+"mm");
					    System.out.println("Average of maximum temprature:"+ Math.round(TmaxAv) + "degC");
					    System.out.println("Max Tmax value "+maxTmax+"degC");
					    System.out.println("Average of minimum temprature:"+ Math.round(TminAv)+ "degC");
					    System.out.println("Max Tmin value "+maxTmin+"degC");
					    System.out.println("Average of sunny days:" + Math.round(SunAv) + "Hours");
					    System.out.println("Max Sun value "+maxSun+"Hours");
					    System.out.println("Average of Af :" + AfdayAv + "Days");
					    System.out.println("Max Af value "+maxAf+"Days");
					    // end of if statement which is executed only when the year changes
					}
					//print a separator
				System.out.println("------------- ");
				
				//reset all variables as the year changes
				 count=0;
				 sumRain=0;
				 sumTmax=0;
				 sumTmin=0;
				 sumSun=0;
				 sumAfday=0;
				 maxRain=0;
				 maxTmax=0;
				 maxTmin=0;
				 maxSun=0;
				 maxAf=0;
				 //Print out the value of the new year
				System.out.println(records.get(i).getYear());
				}
				// with the use of += we are adding up all the values of each column separately
				// we need them for calculating average
				sumRain += records.get(i).getRain();
				sumTmax += records.get(i).getTmax();
				sumTmin += records.get(i).getTmin();
				sumAfday += records.get(i).getafDays();
				sumSun += records.get(i).getSun();
				year=records.get(i).getYear();
				
				
				//for the last year in the file non the of the above code works as they work only work when the year changes
				//we do calculation for the last year in the file
				if(records.get(i).getRain() > maxRain){
					maxRain = records.get(i).getRain();
				}
				else if(records.get(i).getTmax() >  maxTmax){
					maxTmax = records.get(i).getTmax();
					
				}
				else if(records.get(i).getTmin() >  maxTmin){
					maxTmin = records.get(i).getTmin();
				}
				else if (records.get(i).getSun() > maxSun){
					maxSun = records.get(i).getSun();
				}
				else if (records.get(i).getafDays() > maxAf){
					maxAf = records.get(i).getafDays();
				}
				
				count++;
			    }
			   //again if count is not zero.
				if(count != 0)
				{
					
					
					RainAv = sumRain/count;
					TmaxAv = sumTmax/count;
					TminAv = sumTmin/count;
					SunAv = sumSun/count;
        	    	AfdayAv= sumAfday/count;
					System.out.println("Average of rain fall:" + Math.round(RainAv) + "mm");
					System.out.println("Max rain fall value "+maxRain+"mm");
				    System.out.println("Average of maximum temprature:"+ Math.round(TmaxAv) + "degC");
				    System.out.println("Max Tmax value "+maxTmax+"degC");
				    System.out.println("Average of minimum temprature:"+ Math.round(TminAv)+ "degC");
				    System.out.println("Max Tmin value "+maxTmin+"degC");
				    System.out.println("Average of sunny days:" + Math.round(SunAv) + "Hours");
				    System.out.println("Max Sun value "+maxSun+"Hours");
				    System.out.println("Average of Af :" + Math.round(AfdayAv) + "Days");
				    System.out.println("Max Af value "+maxAf+"Days");
				}
                
     


	}					
}
