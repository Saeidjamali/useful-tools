package uk.ac.mmu;

import java.text.DecimalFormat;
import java.util.List;

public class Utils {

	public static List<Sensor> findAvPerHour(List<Double> array, List<Integer> timelist) {

		double average = 0;
		double sum = 0;
		int hour = 0;
		int count = 0;
		Datalist list = new Datalist();

		for (int i = 0; i < array.size() && i < timelist.size(); i++) {
			Sensor sen = new Sensor();
			DecimalFormat f = new DecimalFormat("00.00");
			if (timelist.get(i) != hour) {
				if (count != 0) {
					average = sum / count;
					String av = f.format(average);
					sen.setAverageph(Double.parseDouble(av));
					list.getsList().add(sen);//
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
			Sensor sen2 = new Sensor();
			average = sum / count;
			String av = f.format(average);
			sen2.setAverageph(Double.parseDouble(av));//
			list.getsList().add(sen2);//
			count++;
		}
		return list.getsList();
	}

	public static List<Sensor> findMdateFromArray(List<String> array, List<Integer> timearray) {

		Datalist list = new Datalist();
		String value = "";
		int hour = 0;

		for (int i = 0; i < array.size() && i < timearray.size(); i++) {
			Sensor sensor = new Sensor();
			if (timearray.get(i) != hour) {
				value = array.get(i);
				sensor.setDate(value);
				list.getsList().add(sensor);
				hour = timearray.get(i);
			}
		}
		return list.getsList();
	}

	public static List<Sensor> findMinPerHour(List<Double> array, List<Integer> timelist) {

		double min = 0;
		int hour = 0;
		int count = 0;
		Datalist list = new Datalist();

		for (int i = 0; i < array.size() && i < timelist.size(); i++) {
			Sensor sen = new Sensor();
			if (timelist.get(i) != hour) {
				if (count != 0) {
					sen.setMinph(min);
					list.getsList().add(sen);//
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
			Sensor sen2 = new Sensor();
			sen2.setMinph(min);//
			list.getsList().add(sen2);//
		}
		return list.getsList();
	}

	public static List<Sensor> findMTime(List<Integer> array) {

		int mtime;
		int count = 0;
		mtime = 0;
		Datalist list = new Datalist();

		for (int i = 1; i < array.size(); ++i) {
			Sensor sensor = new Sensor();
			if (array.get(i) != mtime) {
				mtime = array.get(i);
				sensor.setMtime(mtime);
				list.getsList().add(sensor);
			}
			mtime = array.get(i);
			count++;
		}
		return list.getsList();
	}

	public static List<Sensor> findMaxPerHour(List<Double> array, List<Integer> timelist) {

		double max = 0;
		int hour = 0;
		int count = 0;
		Datalist list = new Datalist();

		for (int i = 0; i < array.size() && i < timelist.size(); i++) {
			Sensor sen = new Sensor();
			if (timelist.get(i) != hour) {
				if (count != 0) {
					sen.setMaxph(max);
					list.getsList().add(sen);//
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
			Sensor sen2 = new Sensor();
			sen2.setMaxph(max);//
			list.getsList().add(sen2);//
		}
		return list.getsList();
	}

	public static double findMaxFromArray(List<Double> array) {

		double max;
		max = array.get(0);

		for (int i = 1; i < array.size(); ++i) {
			if (array.get(i) > max) {
				max = array.get(i);
			}

		}
		return max;
	}

	public static List<Sensor> findZeroValue(List<Double> array, List<String> timearray) {

		Datalist list = new Datalist();
		String zerovalue = "";

		for (int i = 0; i < array.size() && i < timearray.size(); i++) {
			Sensor sensor = new Sensor();
			if (array.get(i) == 0) {
				zerovalue = timearray.get(i);
				sensor.setZeroValue(zerovalue);
				list.getsList().add(sensor);
			}
		}
		return list.getsList();
	}

	public static double findMinFromArray(List<Double> array) {
		double min;
		min = array.get(0);
		for (int i = 1; i < array.size(); ++i) {
			if (array.get(i) < min) {
				min = array.get(i);
			}

		}
		return min;
	}

	public static double findAverageFromArray(List<Double> array) {
		double average;
		double sum = 0;
		for (int i = 0; i < array.size(); ++i) {
			sum += array.get(i);
		}
		average = sum / array.size();
		return average;
	}

	public static List<Sensor> findDateTime(List<String> array) {
		Datalist list = new Datalist();

		for (int i = 0; i < array.size(); i++) {

			String splits[] = array.get(i).split("\\s+");
			Sensor sensors = new Sensor();

			String date = splits[0];
			String time = splits[1];

			sensors.setDate(date);
			sensors.setTime(time);

			String fields[] = time.split(":");

			int hour = Integer.parseInt(fields[0]);
			int min = Integer.parseInt(fields[1]);

			sensors.setHour(hour);
			sensors.setMin(min);

			list.getsList().add(sensors);
		}
		return list.getsList();
	}

}