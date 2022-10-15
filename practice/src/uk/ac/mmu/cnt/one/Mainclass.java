package uk.ac.mmu.cnt.one;

import java.util.List;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Mainclass {


		
		


		 public static void main(String[] args) throws IOException {
				 // TODO Auto-generated method stub
				 
				 List<MonthlyRecord>   records = new ArrayList<MonthlyRecord>();
				 List<String> ignoreAll = Arrays.asList("--", "*");
				 Scanner scan = new Scanner(System.in);
				 System.out.println("enter the city");
				 String filename = scan.next();
				 try{	BufferedReader file = new BufferedReader(new FileReader( filename + "data.txt"));
				 String line;
				 
					 while((line = file.readLine()) != null) {
						 
						
						 records.add(new MonthlyRecord(line));
						 
						 
				 }
				 file.close();
				 } catch (FileNotFoundException e){
					 System.out.println("Data not found. Please import a file!");
				 }
				 
				 scan.close();
				 
				 
				 
				 System.out.println(records);
		

	}

}
