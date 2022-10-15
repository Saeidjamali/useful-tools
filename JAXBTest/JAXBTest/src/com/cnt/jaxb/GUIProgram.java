package com.cnt.jaxb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.TextArea;

public class GUIProgram extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldCourse;
	private JTextField textFieldDate;

	private static final String STUDENT_XML = "./student-jaxb2.xml";
	
	ArrayList<Student> sList = new ArrayList<Student>();
	private JButton btnNewButtonSaveXML;
	private TextArea textAreaDisplay;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIProgram frame = new GUIProgram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIProgram() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(97, 35, 86, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(97, 73, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(97, 109, 86, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldCourse = new JTextField();
		textFieldCourse.setBounds(97, 150, 86, 20);
		contentPane.add(textFieldCourse);
		textFieldCourse.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(97, 188, 86, 20);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Object");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//button is pressed
				//so i need to create a list of students
				//i need a variable
				
				Student s = new Student();
				s.setId(textFieldID.getText());
				s.setName(textFieldName.getText());
				s.setCourse(textFieldCourse.getText());
				s.setEmail(textFieldEmail.getText());
				s.setDate(textFieldDate.getText());
				
				//i have got s student object will all values set, coming from textareas..
				//add this s object into the arraylist
				
				sList.add(s);//added now...
				
				JOptionPane.showMessageDialog(null, "Object Created and Added to the list");
				textFieldID.setText("");
				textFieldName.setText("");
				textFieldCourse.setText("");
				textFieldEmail.setText("");
				textFieldDate.setText("");
				//more press event will add one more object s into it...and so on...until user stops...
				
				//next job is to create xml out of this object...
				
			}
		});
		btnNewButton.setBounds(40, 228, 112, 23);
		contentPane.add(btnNewButton);
		
		btnNewButtonSaveXML = new JButton("Save XML");
		btnNewButtonSaveXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//i create object out of studentlist class, because this is the object that i want to marshall
				StudentList list = new StudentList();
				list.setName("test");
				list.setSList(sList);
				
				
				
				// create JAXB context and instantiate marshaller
			    JAXBContext context;
				try {
					context = JAXBContext.newInstance(StudentList.class);				
				    Marshaller m = context.createMarshaller();
				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				 
				    m.marshal(list, System.out);

				    // Write to XML File
				    m.marshal(list, new File(STUDENT_XML));
				    
				    JOptionPane.showMessageDialog(null, "XML File Created out of the object");
				    
			      } catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			}
		});
		btnNewButtonSaveXML.setBounds(40, 262, 112, 23);
		contentPane.add(btnNewButtonSaveXML);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(26, 41, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(26, 79, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(26, 115, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Course");
		lblNewLabel_2.setBounds(26, 156, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setBounds(26, 194, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textAreaDisplay = new TextArea();
		textAreaDisplay.setBounds(194, 45, 285, 240);
		contentPane.add(textAreaDisplay);
		
		JButton btnLoadXML = new JButton("Load XML");
		btnLoadXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				    
				    JAXBContext context;
					try {
						context = JAXBContext.newInstance(StudentList.class);
						
					    textAreaDisplay.append("Output from the XML File\n\n");
					    Unmarshaller um = context.createUnmarshaller();
					    StudentList list2 = (StudentList) um.unmarshal(new FileReader(STUDENT_XML));   
					    
				        ArrayList<Student> studentL = list2.getStudents();
				        textAreaDisplay.append(list2.getName()+"\n\n");
				        for (Student temp : studentL) {
				        	textAreaDisplay.append(temp.toString()+"\n\n");
				        }
				    } catch (JAXBException | FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				    
			}
		});
		btnLoadXML.setBounds(295, 11, 102, 23);
		contentPane.add(btnLoadXML);
	}
}
