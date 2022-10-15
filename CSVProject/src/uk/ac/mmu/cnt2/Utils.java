package uk.ac.mmu.cnt2;

import java.util.List;


public class Utils {
	
	/****
	 * finds the maximum value in an arraylist
	 * argument - arraylist of double
	 * @return - double
	 ***/
	public static double findMaxFromArray(List<Double> array){
		double max;
		max = array.get(0);
		for(int i=1;i<array.size();++i)
		{
			if(array.get(i)>max)
			{
				max = array.get(i);
			}
			
		}
		return max;
	}
	
	/****
	 * finds the minimum value in an arraylist
	 * argument - arraylist of double
	 * @return - double
	 ***/
	public static double findMinFromArray(List<Double> array){
		double min;
		min = array.get(0);
		for(int i=1;i<array.size();++i)
		{
			if(array.get(i)<min)
			{
				min = array.get(i);
			}
			
		}
		return min;
	}
	
	/****
	 * finds the average value of all elements in an arraylist
	 * argument - arraylist of double
	 * @return - double
	 ***/
	public static double findAverageFromArray(List<Double> array){
		double average;
		double sum=0;
		for(int i=0;i<array.size();++i)
		{
			sum += array.get(i);
			
		}
		average = sum/array.size();		
		return average; //return the formatted value
	}

}
