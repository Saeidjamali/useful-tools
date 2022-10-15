package uk.ac.mmu.cnt2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	
	//this is a CSVReader class to read the class file.
	//i will put this csv in the data folder in my project	
	//create a method which when called sets the arraylist to array of data object where each line of csv is one object
	
	private List<Data> dataList = new ArrayList<Data>();
	private String path;
	
	public CSVReader(String path) {
		this.path = path;
	}

	public List<Data> getDataList() {
		return dataList;
	}		

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void readCSV() throws IOException,FileNotFoundException{
		//
		BufferedReader in
		   = new BufferedReader(new FileReader(path));	
		
		String line = null;
		
		while((line = in.readLine())!=null){

			//we will have the while line including commas in the line string
			//we will need to split this
			//here we use the split method of the string
			
			String[] splits = line.split(",");//split and put in array
			
			//i know that there will be 7 elements on splits array, from index 0 to 6
			
			//create a data temp object
			Data d = new Data();
			int year = Integer.parseInt(splits[0]);
			int month = Integer.parseInt(splits[1]);
			double tempmax = Double.parseDouble(splits[2]);
			double tempmin = Double.parseDouble(splits[3]);
			double af = Double.parseDouble(splits[4]);
			double rainmm = Double.parseDouble(splits[5]);
			double sunhour = Double.parseDouble(splits[6]);
			
			//now set the object d values
			d.setYear(year);
			d.setMonth(month);
			d.setTempmax(tempmax);
			d.setTempmin(tempmin);
			d.setAf(af);
			d.setRainmm(rainmm);
			d.setSunhour(sunhour);
			
			//now add this object on the dataList
			
			dataList.add(d);
			
			//thats it job done.
			
		}		
		
	}

}