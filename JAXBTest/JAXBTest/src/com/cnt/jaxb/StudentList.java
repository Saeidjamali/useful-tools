package com.cnt.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "com.cnt.jaxb")
@XmlType(propOrder = { "name","students" })
public class StudentList {
	
	// XmLElementWrapper generates a wrapper element around XML representation
	@XmlElementWrapper(name = "students")
	// XmlElement sets the name of the entities
	
	@XmlElement(name = "student")
	private ArrayList<Student> students;
	
	private String name;
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public void setSList(ArrayList<Student> students) {
		this.students = students;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}  
}
