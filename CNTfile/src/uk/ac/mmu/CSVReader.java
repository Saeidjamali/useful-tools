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

	public List<Sensor> readCSV(String csvfile) throws IOException, FileNotFoundException {
		// Open and read the file
		InputStream in = new FileInputStream(csvfile);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

		String line = null;

		for (int i = 0; (line = buffer.readLine()) != null; i++) {
			if (i >= 1) {

				//Split the data by "," creat a new object of sensor and put splits it their fields
				String[] splits = line.split(",");

				Sensor sensors = new Sensor();

				String millis = splits[0];
				String stamp = splits[1];
				String datetime = splits[2].trim();
				String light = splits[3];
				String temp = splits[4];
				String vcc = splits[5];

				// set the object sensor values
				sensors.setMillis(millis);
				sensors.setStamp(stamp);
				sensors.setDatetime(datetime);
				sensors.setLight(light);
				sensors.setTemp(temp);
				sensors.setVcc(vcc);

				// add the object on the dataList

				list.getsList().add(sensors);
			}
		}
		buffer.close();
		return list.getsList();
	}

}