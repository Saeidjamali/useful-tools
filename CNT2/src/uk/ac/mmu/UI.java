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

import org.jfree.ui.RefineryUtilities;



import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class UI extends JFrame {

	private JPanel contentPane;
	private JTextField textopen;
	final static JFileChooser fc = new JFileChooser();
	private JTextArea textArea;
	private JButton btnLoad;
	private JButton btnVis;
	private JButton btnopen;
	private JScrollPane scrollPane;
	private JButton btnSum;
	private JButton btnvisSum;
	// static List<String> datetime = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

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
		btnopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(contentPane);// show the

				if (returnVal == JFileChooser.APPROVE_OPTION) {// this means
																// user have
																// selected a
																// file
					File file = fc.getSelectedFile();
					textopen.setText(file.getAbsolutePath());
					btnLoad.setEnabled(true);

				}

			}
		});
		contentPane.add(btnopen);

		textArea = new JTextArea();
		textArea.setBounds(10, 89, 453, 195);
		contentPane.add(textArea);

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
				if (fc.getFileFilter().getDescription() == ("XML")) {

					try {
						File file = fc.getSelectedFile();
						textopen.setText(file.getAbsolutePath());

						XMLReader read = new XMLReader(); // create object out
															// of our class...
						List<Sensor> readSensors = read.readsensors(file.getAbsolutePath());
						textArea.setText(" ");
						for (Sensor data : readSensors) {
							textArea.append(data + "\n\n");
							list.getsList().add(data);
							list.getTemp().add(Double.parseDouble(data.getTemp()));
							list.getLight().add(Double.parseDouble(data.getLight()));
							list.getVcc().add(Double.parseDouble(data.getVcc()));
							list.getDatetime().add(data.getDatetime());
						}
					

					} catch (Exception s) {
						// TODO Auto-generated catch block
						s.printStackTrace();
						textArea.append("\nError.. Please try again...");
					}

				} else if (fc.getFileFilter().getDescription().equals("CSV")) {
					File file = fc.getSelectedFile();

					CSVReader read = new CSVReader();
					try {
						List<Sensor> readCsv = read.readCSV(file.getAbsolutePath());
						textArea.setText(" ");
						for (Sensor data : readCsv) {
							textArea.append(data + "\n\n");
							list.getsList().add(data);
							list.getTemp().add(Double.parseDouble(data.getTemp()));
							list.getLight().add(Double.parseDouble(data.getLight()));
							list.getVcc().add(Double.parseDouble(data.getVcc()));
							list.getDatetime().add(data.getDatetime());
						}

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						textArea.append("File Not Found");
					} catch (IOException e1) {

						e1.printStackTrace();
						textArea.append("try again");
					}
				} else if (fc.getFileFilter().getDescription().equals("JSON")) {
					File file = fc.getSelectedFile();
					JSONReader read = new JSONReader();
					try {
						List<Sensor> readJson = read.readjason(file.getAbsolutePath());
						textArea.setText(" ");
						for (Sensor data : readJson) {
							textArea.append(data + "\n\n");
							list.getsList().add(data);
							list.getTemp().add(Double.parseDouble(data.getTemp()));
							list.getLight().add(Double.parseDouble(data.getLight()));
							list.getVcc().add(Double.parseDouble(data.getVcc()));
							list.getDatetime().add(data.getDatetime());

						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						textArea.append("try again");
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
				btnVis.setEnabled(true);
				btnvisSum.setEnabled(true);
				// Datalist list = new Datalist();
				Sensor sensor = new Sensor();
				for (Sensor d : Utils.findDateTime(list.getDatetime())) {
					list.getTime().add(d.getHour());
					list.getDate().add(d.getDate());
				}
				for(Sensor date :Utils.findMdateFromArray(list.getDate(), list.getTime())){
					list.getMdate().add(date.getDate());
				}
				for (Sensor mtime : Utils.findMTime(list.getTime())){					
					list.getMtime().add(mtime.getMtime());					
				}
				
				textArea.setText(" ");
				textArea.append("The maximum value of temperature in all years is>"
						+ Utils.findMaxFromArray(list.getTemp()) + "\n");
				textArea.append("The minimum value of temperature in all years is>"
						+ Utils.findMinFromArray(list.getTemp()) + "\n");
				textArea.append("The average value of temperature in all years is>"
						+ Utils.findAverageFromArray(list.getTemp()) + "\n");
				textArea.append("\n");
				textArea.append("The maximum value of vcc in all years is>"
						+ Utils.findMaxFromArray(list.getVcc()) + "\n");
				textArea.append("The minimum value of vcc in all years is>"
						+ Utils.findMinFromArray(list.getVcc()) + "\n");
				textArea.append("The average value of vcc in all years is>"
						+ Utils.findAverageFromArray(list.getVcc()) + "\n");
				textArea.append("\n");
				textArea.append(
						"The maximum value of light in all years is>" + Utils.findMaxFromArray(list.getLight()) + "\n");
				textArea.append(
						"The minimum value of light in all years is>" + Utils.findMinFromArray(list.getLight()) + "\n");
				textArea.append("The average value of light in all years is>"
						+ Utils.findAverageFromArray(list.getLight()) + "\n");
				textArea.append("\n");
				for (Sensor zL : Utils.findZeroValue(list.getLight(), list.getDatetime())) {
					textArea.append("The value of light is zero at>" + zL.getZeroValue() + "\n");
				}
				textArea.append("\n");
				for (Sensor zT : Utils.findZeroValue(list.getTemp(), list.getDatetime())) {
					textArea.append("The value of temperature is zero at>" + zT.getZeroValue() + "\n");
				}
				textArea.append("\n");
				for (Sensor zV : Utils.findZeroValue(list.getVcc(), list.getDatetime())) {
					textArea.append("The value of temperature is zero at>" + zV.getZeroValue() + "\n");
				}
				textArea.append("\n");
			
				for(Sensor av : Utils.findAvPerHour(list.getTemp(), list.getTime())){
					list.getAverageph().add(av.getAverageph());
					Sensor sen = new Sensor();
					sen.setTempav(Double.toString(av.getAverageph()));
					list.getStatics().add(sen);
				//	list.getsList().add(sen);
				}
				textArea.append("\n");
				for (Sensor max : Utils.findMaxPerHour(list.getTemp(), list.getTime())) {				
					list.getMaxph().add(max.getMaxph());
					Sensor sen = new Sensor();
					sen.setTempmax(Double.toString(max.getMaxph()));
					list.getStatics().add(sen);
					//list.getsList().add(sen);
				}
				for (Sensor min : Utils.findMinPerHour(list.getTemp(), list.getTime())) {	
					list.getMinph().add(min.getMinph());
					Sensor sen = new Sensor();
					sen.setTempmin(Double.toString(min.getMinph()));
					list.getStatics().add(sen);
					//list.getsList().add(sen);
					
				}
				textArea.append("\tTemperature Reading Per Hour\n\n");
				textArea.append("Hours\tMaxValue\tMinValue\tAverage\tDate\n");
				for(int i =0; i < list.getMtime().size() && i < list.getMaxph().size()
						&& i < list.getMdate().size(); i++){
					textArea.append("\n"+list.getMtime().get(i)+"\t"+
										 list.getMaxph().get(i)+"\t"+
							             list.getMinph().get(i)+"\t"+
										 list.getAverageph().get(i)+"\t"+
										 list.getMdate().get(i)+"\n");
					textArea.append("____________________________________________________________");
				
				}
				textArea.append("\n\n\n");
				textArea.append("\tLight Reading Per Hour\n\n");
				list.getMaxph().clear();
				list.getMinph().clear();
				list.getAverageph().clear();
		    	for (Sensor max : Utils.findMaxPerHour(list.getLight(), list.getTime())) {
					Sensor sen = new Sensor();
					list.getMaxph().add(max.getMaxph());
					sen.setLightmax(Double.toString(max.getMaxph()));
					list.getStatics().add(sen);
					//list.getsList().add(sen);
				}
			
				for (Sensor min : Utils.findMinPerHour(list.getLight(), list.getTime())) {	
					list.getMinph().add(min.getMinph());
					Sensor sen = new Sensor();
					sen.setLightmin(Double.toString(min.getMinph()));
					list.getStatics().add(sen);
					//list.getsList().add(sen);
				}
				
				for(Sensor av : Utils.findAvPerHour(list.getLight(), list.getTime())){
					Sensor sen = new Sensor();
					list.getAverageph().add(av.getAverageph());
					sen.setLightav(Double.toString(av.getAverageph()));
				   list.getStatics().add(sen);
				 //  list.getsList().add(sen);
				}
				textArea.append("Hours\tMaxValue\tMinValue\tAverage\tDate\n");
				for(int i =0; i < list.getMtime().size() && i < list.getMaxph().size()
						&& i < list.getMdate().size(); i++){
					textArea.append("\n"+list.getMtime().get(i)+"\t"+
										 list.getMaxph().get(i)+"\t"+
							             list.getMinph().get(i)+"\t"+
										 list.getAverageph().get(i)+"\t"+
									     list.getMdate().get(i)+"\n");
					textArea.append("____________________________________________________________");
				
				}
				textArea.append("\n\n\n");
				textArea.append("\tVCC Reading Per Hour\n\n");
				list.getMaxph().clear();
				list.getMinph().clear();
				list.getAverageph().clear();
		    	for (Sensor max : Utils.findMaxPerHour(list.getVcc(), list.getTime())) {
					Sensor sen = new Sensor();
					list.getMaxph().add(max.getMaxph());
					sen.setVccmax(Double.toString(max.getMaxph()));
					list.getStatics().add(sen);
				//	list.getsList().add(sen);
				}
			
				for (Sensor min : Utils.findMinPerHour(list.getVcc(), list.getTime())) {	
					list.getMinph().add(min.getMinph());
					Sensor sen = new Sensor();
					sen.setVccmin(Double.toString(min.getMinph()));
					list.getStatics().add(sen);
					
				//	list.getsList().add(sen);
				}
				for(Sensor av : Utils.findAvPerHour(list.getVcc(), list.getTime())){
					Sensor sen = new Sensor();
					list.getAverageph().add(av.getAverageph());
					sen.setVccav(Double.toString(av.getAverageph()));
				   list.getStatics().add(sen);
				//   list.getsList().add(sen);
				}
				textArea.append("Hours\tMaxValue\tMinValue\tAverage\tDate\n");
				for(int i =0; i < list.getMtime().size() && i < list.getMaxph().size()
						&& i < list.getMdate().size(); i++){
					textArea.append("\n"+list.getMtime().get(i)+"\t"+
										 list.getMaxph().get(i)+"\t"+
							             list.getMinph().get(i)+"\t"+
										 list.getAverageph().get(i)+"\t"+
									     list.getMdate().get(i)+"\n");
					textArea.append("____________________________________________________________");
				
				}
				

			}

		});
		btnVis = new JButton("Visualize");
		btnVis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
				
				LineChartSum linechartdemo1 = new LineChartSum("temp statics");
				linechartdemo1.setList(list.getStatics());
				linechartdemo1.setTimelist(list.getMtime());
				linechartdemo1.create();
				linechartdemo1.pack();
				RefineryUtilities.centerFrameOnScreen(linechartdemo1);
				linechartdemo1.setVisible(true);
				
			/*	LineChartSum linechartdemo2 = new LineChartSum("Light static");
				linechartdemo2.setList(list.getLight());
				linechartdemo2.setTimelist(list.getMtime());
				linechartdemo2.create();
				linechartdemo2.pack();
				RefineryUtilities.centerFrameOnScreen(linechartdemo2);
				linechartdemo2.setVisible(true);
				
				LineChartSum linechartdemo3 = new LineChartSum("VCC");
				linechartdemo3.setList(list.getVcc());
				linechartdemo3.setTimelist(list.getMtime());
				linechartdemo3.create();
				linechartdemo3.pack();
				RefineryUtilities.centerFrameOnScreen(linechartdemo3);
				linechartdemo3.setVisible(true);*/
			}
		});
		btnvisSum.setEnabled(false);
		btnvisSum.setBounds(285, 37, 96, 23);
		contentPane.add(btnvisSum);

	}
}
