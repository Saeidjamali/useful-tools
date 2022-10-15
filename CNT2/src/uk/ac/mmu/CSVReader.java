package uk.ac.mmu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class CSVReader {

	//this is a CSVReader class to read the class file.
		//i will put this csv in the data folder in my project	
		//create a method which when called sets the arraylist to array of data object where each line of csv is one object
		Datalist list = new Datalist();
		private String path;
		
		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
		public List<Sensor> readCSV(String csvfile) throws IOException,FileNotFoundException{
		InputStream in = new FileInputStream(csvfile);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
				
			
			String line = null;
			
			for( int i=0;(line = buffer.readLine()) != null; i++  ){
                 if(i>=1 ){
			
				
				String[] splits = line.split(",");//split and put in array
				
				
				Sensor sensors = new Sensor();
				
				String millis = splits[0];
				String stamp = splits[1];
				String datetime = splits[2].trim();
				String light = splits[3];
				String temp = splits[4];
				String vcc = splits[5];
				
				
				//now set the object d values
				sensors.setMillis(millis);
				sensors.setStamp(stamp);
				sensors.setDatetime(datetime);
				sensors.setLight(light);
				sensors.setTemp(temp);
				sensors.setVcc(vcc);
				
				
				//now add this object on the dataList
				
				list.getsList().add(sensors);
				
				//thats it job done.
				
			}
			}
			return list.getsList();		
		}
	
}