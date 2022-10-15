package com.cnt.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "studentList")
//If you want you can define the order in which the fields are written
//Optional
@XmlType(propOrder = { "id", "name", "email", "course","date" })

public class Student {
	
	private String id;
	private String name;
	private String course;
	private String email;
	private String date;
	
	@XmlElement(name = "id")
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	// If you like the variable name, e.g. "name", you can easily change this
	// name for your XML-Output:
	@XmlElement(name = "studentName")
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getCourse(){
		return course;
	}
	
	public void setCourse(String course){
		this.course = course;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date = date;
	}

	public String toString() {
		return "[id=" + id + " name=" + name + " course=" + course
				+ " email=" + email + " date=" + date+"]";
	}
		
}