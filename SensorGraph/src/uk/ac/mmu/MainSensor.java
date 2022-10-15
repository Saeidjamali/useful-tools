package uk.ac.mmu;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import com.fazecast.jSerialComm.SerialPort;
import com.sun.glass.events.KeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.KeyAdapter;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MainSensor {

	static SerialPort chosenPort;
	static int x = 0;
	static Datalist list = new Datalist();
	final static JFileChooser fc = new JFileChooser();
	private static JButton btnReadings;
	private static ChartPanel chartPanel;
	private static TextArea textDisplay;
	private static JButton btnHealthInfo;
	private static JTextField txtAge;
	static int age;
	static String sex;
	static double hbeat;
	static double temp;
	static double hum;
	private static final ButtonGroup buttonGroup = new ButtonGroup();
	private static JButton btnCheckup;
	private static JButton btnfile;

	public static void main(String[] args) {

		// create and configure the window
		JFrame window = new JFrame();
		window.setTitle("Health Monitoring System");
		window.setSize(600, 400);
		window.getContentPane().setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
		fc.addChoosableFileFilter(filter);
		// create a drop-down box and connect button, then place them at the top
		// of the window
		JComboBox<String> portList = new JComboBox<String>();
		JButton connectButton = new JButton("Go live");
		JPanel topPanel = new JPanel();
		topPanel.add(portList);
		topPanel.add(connectButton);
		window.getContentPane().add(topPanel, BorderLayout.NORTH);

		btnReadings = new JButton("Readings");
		btnReadings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btnReadings.getText().equals("Readings")) {
					btnReadings.setText("Chart");
					textDisplay.setVisible(true);

				} else if (btnReadings.getText().equals("Chart")) {
					btnReadings.setText("Readings");
					textDisplay.setVisible(false);
				}
			}
		});
		topPanel.add(btnReadings);

		txtAge = new JTextField();
		txtAge.setEnabled(false);

		btnCheckup = new JButton("Check-up");
		btnCheckup.setEnabled(false);
		btnCheckup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				age = Integer.parseInt(txtAge.getText());
				//textDisplay.append("You are\t" + age + "years old" + "\n");
				if(age > 0 && age >= 17){
					if((hbeat > 70 && hbeat <= 100)){
						textDisplay.append("your heart rate is steady");
						//your heart rate is steady
					}
					else if(hbeat < 70 || hbeat >101){
						textDisplay.append("your heart rate is not steady please check your GP");
						//your heart rate is not steady please check your GP
					}
					if(temp > 20 && temp <=38){
						textDisplay.append("your temp is steady");
						//your temp is steady
					}
					else if (temp <20 || temp>30 ){
						textDisplay.append("your temp is not steady please visit your GP");
						//your temp is not steady please visit your GP
					}
				}
				if(age < 18){
					if((hbeat > 60 && hbeat <= 100)){
						textDisplay.append("your heart rate is steady");
						//your heart rate is steady
					}
					else if(hbeat < 60 || hbeat >101){
						textDisplay.append("your heart rate is not steady please check your GP");
						//your heart rate is not steady please check your GP
					}
					if(temp > 20 && temp <=38){
						textDisplay.append("your temp is steady");
						//your temp is steady
					}
					else if (temp < 20 || temp >39 ){
						textDisplay.append("your temp is not steady please visit your GP");
						//your temp is not steady please visit your GP
					}
				}
					
			}
		});

		btnfile = new JButton("File Reader");
		btnfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Show the file chooser when open button is pressed
				int returnVal = fc.showOpenDialog(window);
				// if file is chosen
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// get the path of the file
					// File file = fc.getSelectedFile();

				} else {
					textDisplay.append("Operation cancelled, Please choose a file");
				}

				if (fc.getFileFilter().getDescription().equals("CSV")) {
					File file = fc.getSelectedFile();
					// create an object out of csv reader class and to the same
					// process
					CSVReader read = new CSVReader();
					try {
						List<Statistics> readCsv = read.readCSV(file.getAbsolutePath());
						textDisplay.setText(" ");
						for (Statistics data : readCsv) {
							textDisplay.append(data + "\n\n");
							list.getsList().add(data);
						}

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						textDisplay.append("File Not Found");
					} catch (IOException e1) {
						e1.printStackTrace();
						textDisplay.append("try again");
					} catch (ArrayIndexOutOfBoundsException e2) {
						textDisplay.append("File format Error, please try again");
					}
				}
				//create an object of chart class
				LineChartDemo linechartdemo1 = new LineChartDemo("All Sensors");
				linechartdemo1.setList(list.getsList());
				linechartdemo1.setTimelist(list.getTime());
				linechartdemo1.create();
				linechartdemo1.pack();
				RefineryUtilities.centerFrameOnScreen(linechartdemo1);
				linechartdemo1.setVisible(true);
			}
		});
		topPanel.add(btnfile);
		topPanel.add(btnCheckup);
		topPanel.add(txtAge);
		txtAge.setText("Enter your age");
		txtAge.setColumns(10);

		// populate the drop-down box
		SerialPort[] portNames = SerialPort.getCommPorts();
		for (int i = 0; i < portNames.length; i++)
			portList.addItem(portNames[i].getSystemPortName());

		// create the line graph
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series = new XYSeries("Hbeat Reading");
		XYSeries series1 = new XYSeries("Temp Readings");
		XYSeries series2 = new XYSeries("Humidity Readings");
		dataset.addSeries(series);
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		JFreeChart chart = ChartFactory.createXYLineChart("Sensor Readings", "Time (seconds)", "Readings", dataset);
		chartPanel = new ChartPanel(chart);
		window.getContentPane().add(chartPanel, BorderLayout.CENTER);
		chartPanel.setLayout(null);

		// Create textArea
		textDisplay = new TextArea();
		textDisplay.setBounds(10, 5, 564, 313);
		textDisplay.setEditable(false);
		chartPanel.add(textDisplay);
		textDisplay.setEnabled(true);
		// textDisplay.setBounds(600, 600, 800, 800);
		textDisplay.setVisible(false);

		// configure the connect button and use another thread to listen for
		// data
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (connectButton.getText().equals("Go live")) {
					File f = new File("log.csv");
					// attempt to connect to the serial port
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if (chosenPort.openPort()) {
						connectButton.setText("Disconnect");
						portList.setEnabled(false);
					}

					// create a new thread that listens for incoming text and
					// populates the graph
					Thread thread = new Thread() {
						@Override
						public void run() {

							Scanner scanner = new Scanner(chosenPort.getInputStream());
							int count = 0;
							while (scanner.hasNextLine()) {

								try {
									Date d = new Date();
									Statistics sensor = new Statistics();
									SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
									String datetime = dt.format(d);
									SimpleDateFormat hour = new SimpleDateFormat("mm");
									String hours = hour.format(new Date());
									String line = scanner.nextLine();
									// System.out.println(line);
									textDisplay.append(datetime + ">" + line + "\n");
									String[] incoming = line.split(",");
									String hbeat = incoming[0]; // this has the
																// celsius
																// reading...
									String temp = incoming[1]; // this has the
																// farenheit
																// reading...
									String hum = incoming[2]; // this is light
									if (count > 1) {
										series.add(x++, Double.parseDouble(hbeat));
										series1.add(x++, Double.parseDouble(temp));
										series2.add(x++, Double.parseDouble(hum));
										sensor.setHbeat(Double.parseDouble(hbeat));
										sensor.setTemp(Double.parseDouble(temp));
										sensor.setHum(Double.parseDouble(hum));
									}
									sensor.setDatetime(datetime);
									sensor.setHour(Integer.parseInt(hours));
									list.getsList().add(sensor);
									// int number = Integer.parseInt(line);
									try (PrintWriter pw = new PrintWriter(f)) {
										for (Statistics dr : list.getsList()) {
											pw.println(dr);
										}

									} catch (FileNotFoundException e1) {
										e1.printStackTrace();
									}
									window.repaint();
								} catch (Exception e) {
								}
								count++;

							}
							scanner.close();
						}
					};

					thread.start();
				} else {
					// disconnect from the serial port
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Go live");
					series.clear();
					x = 0;
					//add all the readings in seperate lists.
					for (int i = 0; i < list.getsList().size(); i++) {
						if (i > 1) {
							list.getHbeat().add(list.getsList().get(i).getHbeat());
							list.getTemp().add(list.getsList().get(i).getTemp());
							list.getHum().add(list.getsList().get(i).getHum());
							list.getTime().add(list.getsList().get(i).getHour());

						}
					}
                       //find the average of all readings
					hbeat = Find.findAvPerHour(list.getHbeat());
					temp = Find.findAvPerHour(list.getTemp());
					textDisplay.setText(" ");
					textDisplay.append("Your average heart beat is: \t" + Find.findAvPerHour(list.getHbeat()) + "\n");
					textDisplay.append("Your average temp is: \t" + Find.findAvPerHour(list.getTemp()) + "\n");
					textDisplay.append("Your average humidity is: \t" + Find.findAvPerHour(list.getHum()) + "\n");
					txtAge.setEnabled(true);
					btnCheckup.setEnabled(true);
 
				}
			}
		});

		window.setVisible(true);
	}
}
