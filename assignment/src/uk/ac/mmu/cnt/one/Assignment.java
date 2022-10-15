package uk.ac.mmu.cnt.one;

import java.util.ArrayList;
import java.util.regex.*;
import java.util.stream.Stream;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Assignment {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		List<MonthlyRecord> records = new ArrayList<MonthlyRecord>();

		Scanner scan = new Scanner(System.in);
		System.out.println("enter the city");
		String filename = scan.next();
		try {
			BufferedReader file = new BufferedReader(new FileReader(filename + "data.txt"));

			String line;
			char mychar = 200;

			for (int i = 0; (line = file.readLine()) != null; i++) {

				if (i >= 7) {

					line = line.trim();

					records.add(new MonthlyRecord(line));

				}
			}
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Data not found. Please import a file!");
		}

		scan.close();

		double RainAv = 0;
		double TmaxAv = 0;
		double TminAv = 0;
		double SunAv = 0;
		int AfdayAv = 0;
		double sumRain = 0;
		double sumTmax = 0;
		double sumTmin = 0;
		double sumSun = 0;
		int sumAfday = 0;
		int year = 0;
		int count = 0;
		double maxRain = 0;
		double maxTmax = 0;
		double maxTmin = 0;
		double maxSun = 0;
		int maxAf = 0;
		for (int i = 0; i < records.size(); i++) {

			// Find the highest value in each column
			// if the value is greater than maxRain, assume maxRain is equal to
			// the value and do it over and over again until there is no
			// greater.
			if (records.get(i).getRain() > maxRain) {
				maxRain = records.get(i).getRain();
			} else if (records.get(i).getTmax() > maxTmax) {
				maxTmax = records.get(i).getTmax();

			} else if (records.get(i).getTmin() > maxTmin) {
				maxTmin = records.get(i).getTmin();
			} else if (records.get(i).getSun() > maxSun) {
				maxSun = records.get(i).getSun();
			} else if (records.get(i).getafDays() > maxAf) {
				maxAf = records.get(i).getafDays();
			}
			// get the first value of year if it is not equal to variable
			// year(originally equals to 0)execute the code below
			if (records.get(i).getYear() != year) {
				// for the first loop value of count is zero so if it is not
				// zero execute the code below
				if (count != 0) {
					// if count is not zero that means it is maximum 12 before
					// the year changes
					// by the time this code is executed the number of count is
					// maximum 12
					RainAv = sumRain / count;
					TmaxAv = sumTmax / count;
					TminAv = sumTmin / count;
					SunAv = sumSun / count;
					AfdayAv = sumAfday / count;

					// print out everything
					System.out.println("Average of rain fall:" + Math.round(RainAv) + "mm");
					System.out.println("Max rain fall value " + maxRain + "mm");
					System.out.println("Average of maximum temprature:" + Math.round(TmaxAv) + "degC");
					System.out.println("Max Tmax value " + maxTmax + "degC");
					System.out.println("Average of minimum temprature:" + Math.round(TminAv) + "degC");
					System.out.println("Max Tmin value " + maxTmin + "degC");
					System.out.println("Average of sunny days:" + Math.round(SunAv) + "Hours");
					System.out.println("Max Sun value " + maxSun + "Hours");
					System.out.println("Average of Af :" + AfdayAv + "Days");
					System.out.println("Max Af value " + maxAf + "Days");
					// end of if statement which is executed only when the year
					// changes
				}
				// print a separator
				System.out.println("------------- ");

				// reset all variables as the year changes
				count = 0;
				sumRain = 0;
				sumTmax = 0;
				sumTmin = 0;
				sumSun = 0;
				sumAfday = 0;
				maxRain = 0;
				maxTmax = 0;
				maxTmin = 0;
				maxSun = 0;
				maxAf = 0;
				// Print out the value of the new year
				System.out.println(records.get(i).getYear());
			}
			// with the use of += we are adding up all the values of each column
			// separately
			// we need them for calculating average
			sumRain += records.get(i).getRain();
			sumTmax += records.get(i).getTmax();
			sumTmin += records.get(i).getTmin();
			sumAfday += records.get(i).getafDays();
			sumSun += records.get(i).getSun();
			year = records.get(i).getYear();
			// for the last year in the file non the of the above code works as
			// they work only when the year changes
			// we do calculation for the last year in the file

			count++;
		}
		// again if count is not zero.
		if (count != 0) {

			System.out.println("Average of rain fall:" + Math.round(RainAv) + "mm");
			System.out.println("Max rain fall value " + maxRain + "mm");
			System.out.println("Average of maximum temprature:" + Math.round(TmaxAv) + "degC");
			System.out.println("Max Tmax value " + maxTmax + "degC");
			System.out.println("Average of minimum temprature:" + Math.round(TminAv) + "degC");
			System.out.println("Max Tmin value " + maxTmin + "degC");
			System.out.println("Average of sunny days:" + Math.round(SunAv) + "Hours");
			System.out.println("Max Sun value " + maxSun + "Hours");
			System.out.println("Average of Af :" + Math.round(AfdayAv) + "Days");
			System.out.println("Max Af value " + maxAf + "Days");
			count++;
		}

	}
}

class MonthlyRecord {

	private int year = 0;
	private int month = 0;
	private double tmax = 0;
	private double tmin = 0;
	private int afDays = 0;
	private double rain = 0;
	private double sun = 0;

	public MonthlyRecord(String records) {

		String[] fields = (records).split("\\s+");
		for (int i = 0; i < fields.length; i++) {
			fields[i] = fields[i].replaceAll("[^\\d.-]", "");
			System.out.println(fields[i]);
		}

		if (!"---".equals(fields[0])) {
			this.year = Integer.valueOf(fields[0]);
		}
		if (!"---".equals(fields[1])) {
			this.month = Integer.valueOf(fields[1]);
		}
		if (!"---".equals(fields[2])) {
			this.tmax = Double.valueOf(fields[2]);
		}
		if (!"---".equals(fields[3])) {
			this.tmin = Double.valueOf(fields[3]);
		}
		if (!"---".equals(fields[4])) {
			this.afDays = Integer.valueOf(fields[4]);
		}
		if (!"---".equals(fields[5])) {
			this.rain = Double.valueOf(fields[5]);
		}
		if (!"---".equals(fields[6])) {
			this.sun = Double.valueOf(fields[6]);
		}

	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public double getTmax() {
		return tmax;
	}

	public double getTmin() {
		return tmin;
	}

	public int getafDays() {
		return afDays;
	}

	public double getRain() {
		return rain;
	}

	public double getSun() {
		return sun;
	}

	public String toString() {
		return new String("year" + year + "month" + month + "tmax" + tmax + "tmin" + tmin + "afDays" + afDays + "rain"
				+ rain + "sun" + sun);
	}

}
