package uk.mmu;

import java.text.DecimalFormat;
import java.util.List;



public class Utils {
	
	public static List<Statistics> findAvPerHour(List<Double> array, List<Integer> timelist) {
		 
		double average = 0;
		double sum = 0;
		int hour = 0;
		int count = 0;
		Datalist list = new Datalist();

		for (int i = 0; i < array.size() && i < timelist.size(); i++) {
			Statistics sta = new Statistics();
			DecimalFormat f = new DecimalFormat("00.00");
			if (timelist.get(i) != hour) {
				if (count != 0) {
					average = sum / count;
					String av = f.format(average);
					sta.setAveragepHour(Double.parseDouble(av));
					list.getsList().add(sta);
				}
				count = 0;
				sum = 0;
			}
			sum += array.get(i);
			hour = timelist.get(i);
			count++;
		}
		if (count != 0) {
			DecimalFormat f = new DecimalFormat("00.00");
			Statistics sta2 = new Statistics();
			average = sum / count;
			String av = f.format(average);
			sta2.setAveragepHour(Double.parseDouble(av));//
			list.getsList().add(sta2);//
			count++;
		}
		
		return list.getsList();	
		
	}
	
	
	public static List<Statistics> findMinPerHour(List<Double> array, List<Integer> timelist) {

		double min = 0;
		int hour = 0;
		int count = 0;
		Datalist list = new Datalist();

		for (int i = 0; i < array.size() && i < timelist.size(); i++) {
			Statistics sta = new Statistics();
			if (timelist.get(i) != hour) {
				if (count != 0) {
					sta.setMinpHour(min);
					list.getsList().add(sta);//
				}
				count = 0;
				min = array.get(i);
			}
			hour = timelist.get(i);
			if (min > array.get(i)) {
				min = array.get(i);
			}
			count++;
		}
		if (count != 0) {
			Statistics sta2 = new Statistics();
			sta2.setMinpHour(min);//
			list.getsList().add(sta2);//
		}
		return list.getsList();
	}

	
	
	public static List<Statistics> findMaxPerHour(List<Double> array, List<Integer> timelist) {

		double max = 0;
		int hour = 0;
		int count = 0;
		Datalist list = new Datalist();

		for (int i = 0; i < array.size() && i < timelist.size(); i++) {
			Statistics sta = new Statistics();
			if (timelist.get(i) != hour) {
				if (count != 0) {
					sta.setMaxpHour(max);
					list.getsList().add(sta);//
				}
				count = 0;
				max = array.get(i);
			}
			hour = timelist.get(i);
			if (max < array.get(i)) {
				max = array.get(i);
			}
			count++;
		}
		if (count != 0) {
			Statistics sta2 = new Statistics();
			sta2.setMaxpHour(max);//
			list.getsList().add(sta2);//
		}
		return list.getsList();
	}
}
