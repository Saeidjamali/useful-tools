package uk.ac.mmu;

import java.text.DecimalFormat;
import java.util.List;



public class Utils {
	
	public static double findAvPerHour(List<Double> array) {
		 
  int average = 0;
  int sum = 0;
  int count = 0;
  for (int i=0; i < array.size(); i++){
	  sum += array.get(i);
	  count++;
  }
	average = sum/count;	
		return average;	
		
	}
	
}
