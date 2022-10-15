package uk.ac.mmu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLStreamException;

import org.jfree.ui.RefineryUtilities;

import com.google.gson.JsonSyntaxException;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class UI extends JFrame {

	private JPanel contentPane;
	private JTextField textopen;
	final static JFileChooser fc = new JFileChooser();
	static String SensorName = "";
	private JTextArea textArea;
	private JButton btnLoad;
	private JButton btnVis;
	private JButton btnopen;
	private JScrollPane scrollPane;
	private JButton btnSum;
	private JButton btnvisSum;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtntemp;
	private JRadioButton rdbtnlight;
	private JRadioButton rdbtnvcc;
	// static List<String> datetime = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
         //This is the Jfilechooser filter so the user can only choose xml, csv, or json files.
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("CSV", "csv");
		FileNameExtensionFilter filter3 = new FileNameExtensionFilter("JSON", "json");
		fc.addChoosableFileFilter(filter);
		fc.addChoosableFileFilter(filter2);
		fc.addChoosableFileFilter(filter3);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
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
	public UI() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\saeid\\Desktop\\Icons8-Ios7-Data-Combo-Chart.ico"));
		setTitle("File Reader - Data analyzer");

		Datalist list = new Datalist();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textopen = new JTextField();
		textopen.setBounds(10, 10, 186, 23);
		contentPane.add(textopen);
		textopen.setColumns(10);
		btnopen = new JButton("Open");
		btnopen.setBounds(10, 37, 71, 23);
		textArea = new JTextArea();
		textArea.setBounds(10, 89, 453, 195);
		contentPane.add(textArea);
		btnopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Show the file chooser when open button is pressed
				int returnVal = fc.showOpenDialog(contentPane);
                      //if file is chosen 
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					//get the path of the file
					File file = fc.getSelectedFile();
					//print it in a text field
					textopen.setText(file.getAbsolutePath());
					btnSum.setEnabled(false);
					btnvisSum.setEnabled(false);
					btnVis.setEnabled(false);
					btnLoad.setEnabled(true);
				} else {
					textArea.append("Operation cancelled, Please choose a file");
				}
			}
		});
		contentPane.add(btnopen);

		

		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLoad.setEnabled(false);
		btnLoad.setBounds(91, 37, 73, 23);
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSum.setEnabled(true);
				list.getsList().clear();
				list.getDatetime().clear();
				  //get the filter description
				 //if it is XML then
				if (fc.getFileFilter().getDescription() == ("XML")) {

					try {
						//get the file path again
						File file = fc.getSelectedFile();
						textopen.setText(file.getAbsolutePath());
                          //creat an object out of xml reader
						XMLReader read = new XMLReader();
						//use to object to run the file reader method
						List<Sensor> readSensors = read.readsensors(file.getAbsolutePath());
						textArea.setText(" ");
						//Read the data in a loop and print it in text field
						for (Sensor data : readSensors) {
							textArea.append(data + "\n\n");
							//add the data to the lists
							list.getsList().add(data);
							list.getDatetime().add(data.getDatetime());
						}
					} catch (Exception s) {
						s.printStackTrace();
						textArea.append("\nError.. Please try again...");
					}
				} else if (fc.getFileFilter().getDescription().equals("CSV")) {
					File file = fc.getSelectedFile();
                       //create an object out of csv reader class and to the same process
					CSVReader read = new CSVReader();
					try {
						List<Sensor> readCsv = read.readCSV(file.getAbsolutePath());
						textArea.setText(" ");
						for (Sensor data : readCsv) {
							textArea.append(data + "\n\n");
							list.getsList().add(data);
							list.getDatetime().add(data.getDatetime());
						}

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						textArea.append("File Not Found");
					} catch (IOException e1) {
						e1.printStackTrace();
						textArea.append("try again");
					}catch(ArrayIndexOutOfBoundsException e2){
						textArea.append("File format Error, please try again");
					}
				} 
				//if the file is json then do the same process with json reader class
				else if (fc.getFileFilter().getDescription().equals("JSON")) {
					File file = fc.getSelectedFile();
					JSONReader read = new JSONReader();
					try {
						List<Sensor> readJson = read.readjason(file.getAbsolutePath());
						textArea.setText(" ");
						for (Sensor data : readJson) {
							textArea.append(data + "\n\n");
							list.getsList().add(data);
							list.getDatetime().add(data.getDatetime());
						}

					} catch (IOException  e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						textArea.append("try again");
					} catch(IllegalStateException  e2){
						e2.printStackTrace();
						textArea.append("Error, Something went wrong\nPlease try again");
				  }catch(JsonSyntaxException e3){
					  e3.printStackTrace();
					  textArea.append("Error, Something went wrong\nPlease try again");
				  }
				} else {
					textArea.append("File format error");
				}
			}

		});
		contentPane.add(btnLoad);

		btnSum = new JButton("Sum");
		btnSum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSum.setEnabled(false);
		btnSum.setBounds(391, 10, 72, 23);
		btnSum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textArea.setText(" ");
				btnVis.setEnabled(true);
				btnvisSum.setEnabled(true);
                   //if temp radio button is selected
				if (rdbtntemp.isSelected()) {
					//clear all lists
					list.getVariable().clear();
					list.getStatics().clear();
					list.getMtime().clear();
					//set the variable name to temp
					SensorName = "Temp Readings";
					//copy temp arraylist to variable list
					for (int i = 0; i < list.getsList().size(); i++) {
						list.getVariable().add(Double.parseDouble(list.getsList().get(i).getTemp()));
					}
				}
				//if radio button light is selected
				else if (rdbtnlight.isSelected()) {
					//set the sensorname to light
					SensorName = "Light Readings";
					//clear all lists
					list.getVariable().clear();
					list.getStatics().clear();
					list.getMtime().clear();
					//go throught a loop and copy light arraylist to variable list
					for (int i = 0; i < list.getsList().size(); i++) {
						list.getVariable().add(Double.parseDouble(list.getsList().get(i).getLight()));
					}
				}
				//if vcc is selected
				else if (rdbtnvcc.isSelected()) {
					//set sensorname to vcc
					SensorName = "VCC Readings";
					//clear all the list
					list.getVariable().clear();
					list.getStatics().clear();
					list.getMtime().clear();
					for (int i = 0; i < list.getsList().size(); i++) {
						list.getVariable().add(Double.parseDouble(list.getsList().get(i).getVcc()));
					}
				}
				else{
					textArea.append("Choose a Sensor Please");
				}
				//clear the lists
				list.getDate().clear();
				list.getTime().clear();
				
				//execute this method to seperate date and time
				for (Sensor d : Utils.findDateTime(list.getDatetime())) {
					list.getTime().add(d.getHour());
					list.getDate().add(d.getDate());
				}
				//execute this method to find the dates in a row rather than repeated date date
				for (Sensor date : Utils.findMdateFromArray(list.getDate(), list.getTime())) {
					list.getMdate().add(date.getDate());
				}
				//execute this method to find the hour in a row rather than repeated hour date
				for (Sensor mtime : Utils.findMTime(list.getTime())) {
					list.getMtime().add(mtime.getMtime());
				}

				textArea.setText(" ");
				//execute these methods to find max, min, average and zero values at all times
				textArea.append("The maximum value of" + SensorName + "at all times is>"
						+ Utils.findMaxFromArray(list.getVariable()) + "\n");
				textArea.append("The minimum value of" + SensorName + "at all times is>"
						+ Utils.findMinFromArray(list.getVariable()) + "\n");
				textArea.append("The average value of" + SensorName + "at all times is>"
						+ Utils.findAverageFromArray(list.getVariable()) + "\n");
				textArea.append("\n");
				for (Sensor zL : Utils.findZeroValue(list.getVariable(), list.getDatetime())) {
					textArea.append("The value of" + SensorName + "is zero at>" + zL.getZeroValue() + "\n");
				}
				textArea.append("\n");
				//execute these methods from utils class to find min, max, average per hour.
				for (Sensor av : Utils.findAvPerHour(list.getVariable(), list.getTime())) {
					list.getAverageph().add(av.getAverageph());
				}
				textArea.append("\n");
				for (Sensor max : Utils.findMaxPerHour(list.getVariable(), list.getTime())) {
					list.getMaxph().add(max.getMaxph());
				}

				for (Sensor min : Utils.findMinPerHour(list.getVariable(), list.getTime())) {
					list.getMinph().add(min.getMinph());
				}
				//set variables min, max, average and add the object in an arraylist
				for (int i = 0; i < list.getMaxph().size() && i < list.getMinph().size()
						&& i < list.getAverageph().size(); i++) {
					Sensor sen = new Sensor();
					double max = list.getMaxph().get(i);
					double min = list.getMinph().get(i);
					double av = list.getAverageph().get(i);
					sen.setStaticmax(Double.toString(max));
					sen.setStaticmin(Double.toString(min));
					sen.setStaticav(Double.toString(av));
					list.getStatics().add(sen);
				}
                    //print the data
				textArea.append("\t" + SensorName + " Per Hour\n\n");
				textArea.append("Hours\tMaxValue\tMinValue\tAverage\tDate\n");
				for (int i = 0; i < list.getMtime().size() && i < list.getMaxph().size()
						&& i < list.getMdate().size(); i++) {
					textArea.append("\n" + list.getMtime().get(i) + "\t" + list.getMaxph().get(i) + "\t"
							+ list.getMinph().get(i) + "\t" + list.getAverageph().get(i) + "\t" + list.getMdate().get(i)
							+ "\n");
					textArea.append("____________________________________________________________");
				}
				list.getMaxph().clear();
				list.getMinph().clear();
				list.getAverageph().clear();
				list.getMdate().clear();
			}
		});
		btnVis = new JButton("Visualize");
		btnVis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// creat an object out of chart class and add lists to the class and creat a chart
				LineChartDemo linechartdemo1 = new LineChartDemo("All Sensors");
				linechartdemo1.setList(list.getsList());
				linechartdemo1.setTimelist(list.getTime());
				linechartdemo1.create();
				linechartdemo1.pack();
				RefineryUtilities.centerFrameOnScreen(linechartdemo1);
				linechartdemo1.setVisible(true);

			}
		});

		btnVis.setEnabled(false);
		btnVis.setBounds(285, 10, 96, 23);
		contentPane.add(btnVis);
		contentPane.add(btnSum);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 88, 453, 195);
		contentPane.add(scrollPane);
		setPreferredSize(new Dimension(450, 110));

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		btnvisSum = new JButton("Vis Sum");
		btnvisSum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                //create an object out of chart class and make the chart
				LineChartSum linechart = new LineChartSum(SensorName);
				linechart.setList(list.getStatics());
				linechart.setTimelist(list.getMtime());
				linechart.create();
				linechart.pack();
				RefineryUtilities.centerFrameOnScreen(linechart);
				linechart.setVisible(true);
			}
		});
		btnvisSum.setEnabled(false);
		btnvisSum.setBounds(285, 37, 96, 23);
		contentPane.add(btnvisSum);

		rdbtntemp = new JRadioButton("Temp");
		buttonGroup.add(rdbtntemp);
		rdbtntemp.setBounds(202, 10, 77, 23);
		contentPane.add(rdbtntemp);
		rdbtntemp.setSelected(true);

		rdbtnlight = new JRadioButton("Light");
		buttonGroup.add(rdbtnlight);
		rdbtnlight.setBounds(202, 37, 77, 23);
		contentPane.add(rdbtnlight);

		rdbtnvcc = new JRadioButton("Vcc");
		buttonGroup.add(rdbtnvcc);
		rdbtnvcc.setBounds(202, 63, 77, 23);
		contentPane.add(rdbtnvcc);

		JButton btnabout = new JButton("About");
		btnabout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				About about = new About();
				about.setVisible(true);
			}
		});
		btnabout.setBounds(391, 37, 71, 23);
		contentPane.add(btnabout);

	}
}
