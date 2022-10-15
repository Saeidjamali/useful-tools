package uk.ac.mmu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
//import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLReader {
	@SuppressWarnings("unchecked")
	public List<Sensor> readsensors(String xmlFile) throws FileNotFoundException, XMLStreamException {
		Datalist list = new Datalist();

		// Create a new XMLInputFactory
		XMLInputFactory inputfactory = XMLInputFactory.newInstance();
		// Create a new eventReader
		InputStream in = new FileInputStream(xmlFile);
		XMLEventReader eventReader = inputfactory.createXMLEventReader(in);

		Sensor sensor = null;
       //Read the file
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();

			//find the root and start element and attributes
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();

				if (startElement.getName().getLocalPart() == ("row")) {
					sensor = new Sensor();
					@SuppressWarnings("unchecked")
					Iterator<Attribute> attributes = startElement.getAttributes();
					while (attributes.hasNext()) {
						//Attribute has no value
						Attribute attribute = attributes.next();
						if (attribute.getName().toString().equals("")) {
							
						}
					}

				}

				//Rad each elements and set them to their coresponding field
				if (event.isStartElement()) {
					if (event.asStartElement().getName().getLocalPart().equals("millis")) {
						event = eventReader.nextEvent();
						sensor.setMillis(event.asCharacters().getData());
						continue;
					}
				}
				if (event.asStartElement().getName().getLocalPart().equals("stamp")) {
					event = eventReader.nextEvent();
					sensor.setStamp(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart().equals("datetime")) {
					event = eventReader.nextEvent();
					sensor.setDatetime(event.asCharacters().getData().trim());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart().equals("light")) {
					event = eventReader.nextEvent();
					sensor.setLight(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart().equals("temp")) {
					event = eventReader.nextEvent();
					sensor.setTemp(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart().equals("vcc")) {
					event = eventReader.nextEvent();
					sensor.setVcc(event.asCharacters().getData());
					continue;
				}
			}
			//end and add everything to the list when there the elements has ended
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart() == ("row")) {
					list.getsList().add(sensor);
				}
			}
		}

		return list.getsList();

	}
}
