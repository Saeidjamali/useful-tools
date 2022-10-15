import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class XmlParser {
public List<Student> readstudents(String xmlFile) throws FileNotFoundException, XMLStreamException{
	
	
	//Create a new XMLInputFactory
	XMLInputFactory inputfactory = XMLInputFactory.newInstance();
	//creat a new eventReader
	InputStream in = new FileInputStream(xmlFile);
	XMLEventReader eventReader = inputfactory.createXMLEventReader(in);
	
	Student student =null;
	
	while(eventReader.hasNext()){
		XMLEvent event = eventReader.nextEvent();
		
		if(event.isStartElement()){
			StartElement startElement = event.asStartElement();
			
			if(startElement.getName().getLocalPart() ==("student")){
				student = new Student();
				@SuppressWarnings("unchecked")
				Iterator<Attribute> attributes = startElement.getAttributes();
				while(attributes.hasNext()){
					Attribute attribute = attributes.next();
					if(attribute.getName().toString().equals("date")){
						student.setDate(attribute.getValue());
					}
				}
				
			}
			
	        if(event.isStartElement()){
	        	if(event.asStartElement().getName().getLocalPart().equals("id")){
	        		event = eventReader.nextEvent();
	        		student.setName(event.asCharacters().getData());
	                continue;
	        	}
	        }
	        if(event.asStartElement().getName().getLocalPart().equals("name")){
	        	event = eventReader.nextEvent();
	        	student.setName(event.asCharacters().getData());
	        	continue;
	        }
	        if (event.asStartElement().getName().getLocalPart().equals("course")) {
	            event = eventReader.nextEvent();
	            student.setCourse(event.asCharacters().getData());
	            continue;
	          }
	        if (event.asStartElement().getName().getLocalPart().equals("email")) {
	            event = eventReader.nextEvent();
	            student.setEmail(event.asCharacters().getData());
	            continue;
	          }
		}
		 if (event.isEndElement()) {
	          EndElement endElement = event.asEndElement();
	          if (endElement.getName().getLocalPart() == ("student")) {
	            Student.students.add(student);
	          }
		 }
	}
	
	return Student.students;
	
}
}
