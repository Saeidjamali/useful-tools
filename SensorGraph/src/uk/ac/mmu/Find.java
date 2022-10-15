package uk.ac.mmu;

import java.util.List;

public class Find {
	

		
		public static double findAvPerHour(List<Double> array) {
			 
	  int average = 0;
	  int sum = 0;
	  int count = 0;
	  // go through a loop and add all elements and divide the results with the number of elements(count)
	  for (int i=0; i < array.size(); i++){
		  sum += array.get(i);
		  count++;
	  }
		average = sum/count;	
			return average;	
			
		}
		
	}


