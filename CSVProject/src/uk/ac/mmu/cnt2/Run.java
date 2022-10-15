package uk.ac.mmu.cnt2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Run {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		CSVReader read = new CSVReader("./data/weatherdata.csv");
		read.readCSV();
		
		//now you can loop through the dataList inside read object to display the values.
		
		for(Data d:read.getDataList()){
			System.out.println(d.toString());
		}
		
		//so you might say what now
		//what big deal is this if we have this in the object. after all, we just displayed them back in comma seperated way
		//However, the real thing is we have now individual row as object with individual attributes
		//can i create one arraylist now just to hold all temperatures then, i.e. just column that i want to work on.
		
		List<Double> tempmax = new ArrayList<Double>();
		List<Double> tempmin = new ArrayList<Double>();
		
		for(Data d: read.getDataList()){
			tempmax.add(d.getTempmax());
			tempmin.add(d.getTempmin());
		}
		
		//now i have two arraylist for tempmax and tempmin
		
		//call the static method from Utils.java to do general statistical analysis
		
		System.out.println("The maximum value in minimum temp of all years is>"+Utils.findMaxFromArray(tempmin));
		System.out.println("The minimum value in minimum temp of all years is>"+Utils.findMinFromArray(tempmin));
		
		System.out.println("The maximum value in maximum temp of all years is>"+Utils.findMaxFromArray(tempmax));
		System.out.println("The minimum value in maximum temp of all years is>"+Utils.findMinFromArray(tempmax));

		System.out.println("Thea average value of maximum temp is>"+Utils.findAverageFromArray(tempmax));
		System.out.println("The minimum value in minimum temp of all years is>"+Utils.findAverageFromArray(tempmin));

	}
}