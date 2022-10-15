package com.cnt.jaxb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Program {

	/**
	 * @param args
	 */	
	private static final String STUDENT_XML = "./student-jaxb.xml";

	  public static void main(String[] args) throws JAXBException, IOException {
		  
		ArrayList<Student> sList = new ArrayList<Student>();
//
//		Student s1 = new Student();
//		s1.setName("John Smith");
//		s1.setId("1");
//		s1.setEmail("john@mmu.ac.uk");
//		s1.setDate("2014-09-10");
//		s1.setCourse("CNT");
//		sList.add(s1);
//		
//		Student s2 = new Student();
//		s2.setName("Mary Smith");
//		s2.setId("2");
//		s2.setEmail("mary@mmu.ac.uk");
//		s2.setDate("2014-19-10");
//		s2.setCourse("BEng");
//		sList.add(s2);
//		
//		
//		
//		
//		
		StudentList list = new StudentList();
	list.setName("test");
		list.setSList(sList);
		
		
		
		// create JAXB context and instantiate marshaller
	    JAXBContext context = JAXBContext.newInstance(StudentList.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	   
	    m.marshal(list, System.out);

	    // Write to XML File
	    m.marshal(list, new File(STUDENT_XML));

	  //this bit now is for reading the XML file	    
	    System.out.println();
	    System.out.println("Output from the XML File: ");
	    Unmarshaller um = context.createUnmarshaller();
	    StudentList list2 = (StudentList) um.unmarshal(new FileReader(STUDENT_XML));   
	    
        ArrayList<Student> studentL = list2.getStudents();
        System.out.println(list2.getName());
        for (Student temp : studentL) {
             System.out.println(temp);
          }
	    
	    
		
	  }
	} 