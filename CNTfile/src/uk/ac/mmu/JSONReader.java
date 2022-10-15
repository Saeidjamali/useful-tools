package uk.ac.mmu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.omg.CORBA.Environment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public class JSONReader {
	//create and object of datalist class
	Datalist list = new Datalist();
	public List<Sensor> readjason(String jasonfile) throws IOException{
		//create an instance of google json reader
		Gson gsonRead = new Gson();
		//open and read the file and finally add it to the list
		InputStream in = new FileInputStream(jasonfile);
		BufferedReader buffer=new BufferedReader(new InputStreamReader(in));
	
		Datalist out = gsonRead.fromJson(buffer, Datalist.class);
		
		return out.getsList();
		
}

}
