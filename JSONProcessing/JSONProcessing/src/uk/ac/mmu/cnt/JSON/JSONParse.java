package uk.ac.mmu.cnt.JSON;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class JSONParse {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		List<Student> sList  = new ArrayList<Student>();
		
		StudentList sl = new StudentList();
		
		Student s = new Student();
        s.setId(1);
        s.setName("Rupak Kharel");
        s.setEmail("r.kharel@mmu.ac.uk");
        s.setCourse("BSc CNT");
        sList.add(s);
        
        Student s1 = new Student();
        s1.setId(2);
        s1.setName("Another one");
        s1.setEmail("another@mmu.ac.uk");
        s1.setCourse("BEng");
        sList.add(s1);
        
        sl.setsList(sList);
        
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        String json = gson.toJson(sl);
        System.out.println(json);
        
        FileWriter writer = new FileWriter(".\\student.json");
		writer.write(json);
		writer.close();
        
        //start reading the file..
		System.out.println("Reading JSON Started...");
        Gson gsonRead = new Gson();
        
        
        BufferedReader br = new BufferedReader(
    			new FileReader(".\\student.json"));

    	//convert the json string back to object
    	StudentList sout = gsonRead.fromJson(br, StudentList.class);

    	sList = sout.getsList();
        
        for(Student s11: sList){
        	System.out.println(s11);
        }        

	}

}