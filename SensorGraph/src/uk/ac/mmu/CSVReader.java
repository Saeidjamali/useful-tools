package uk.ac.mmu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVReader {

	// create an object out of Datalist class
	Datalist list = new Datalist();

	public List<Statistics> readCSV(String csvfile) throws IOException, FileNotFoundException {
		// Open and read the file
		InputStream in = new FileInputStream(csvfile);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

		String line = null;

		for (int i = 0; (line = buffer.readLine()) != null; i++) {
			if (i >= 1) {

				//Split the data by "," creat a new object of sensor and put splits it their fields
				String[] splits = line.split(",");

				Statistics sensors = new Statistics();

				String datetime = splits[0].trim();
				String hbeat = splits[1].trim();
				String temp = splits[2].trim();
				String hum = splits[3].trim();
				

				// set the object sensor values
				sensors.setDatetime(datetime);
				sensors.setHbeat(Double.parseDouble(hbeat));
				sensors.setTemp(Double.parseDouble(temp));
				sensors.setHum(Double.parseDouble(hum));
			

				// add the object on the dataList

				list.getsList().add(sensors);
			}
		}
		buffer.close();
		return list.getsList();
	}

}