package uk.mmu;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




public class SensorGraph {
	
	static SerialPort chosenPort;
	static int x = 0;
    static Datalist list = new Datalist();
    private static JButton btnReadings;
    private static ChartPanel chartPanel;
    private static TextArea textDisplay;
    private static JButton btnHealthInfo;
	public static void main(String[] args) {
		
		// create and configure the window
		JFrame window = new JFrame();
		window.setTitle("Patient Monitoring System");
		window.setSize(600, 400);
		window.getContentPane().setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create a drop-down box and connect button, then place them at the top of the window
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
				if(btnReadings.getText().equals("Readings")) {
					btnReadings.setText("Chart");
				textDisplay.setVisible(true);
					
				}
				else if(btnReadings.getText().equals("Chart")) {
					btnReadings.setText("Readings");
					textDisplay.setVisible(false);
				}
			}
		});
		topPanel.add(btnReadings);
		
		
		// populate the drop-down box
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++)
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
		
		//Create textArea
		textDisplay = new TextArea();
		textDisplay.setEditable(false);
		chartPanel.add(textDisplay);
		textDisplay.setEnabled(true);
		//textDisplay.setBounds(600, 600, 800, 800);
		textDisplay.setVisible(false);
		
		// configure the connect button and use another thread to listen for data
		connectButton.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				if(connectButton.getText().equals("Go live")) {
					File f = new File("log.csv");
					// attempt to connect to the serial port
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if(chosenPort.openPort()) {
						connectButton.setText("Disconnect");
						portList.setEnabled(false);
					}
					
					// create a new thread that listens for incoming text and populates the graph
					Thread thread = new Thread(){
						@Override public void run() {
							
							Scanner scanner = new Scanner(chosenPort.getInputStream());
							int count =0;
							while(scanner.hasNextLine()) {
							 
								try {
									Date d = new Date();
									Statistics sensor = new Statistics();
									SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
									String datetime = dt.format(d);
									SimpleDateFormat hour = new SimpleDateFormat("mm");
								    String hours = hour.format(new Date());
									String line = scanner.nextLine();
									//System.out.println(line);
									textDisplay.append(datetime+">" + line+"\n");
									String[] incoming = line.split(",");
									String hbeat = incoming[0]; // this has the celsius reading...
									String temp = incoming[1]; // this has the farenheit reading...
									String hum = incoming[2]; //this is light
									if(count >1){
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
									//int number = Integer.parseInt(line);
									 try(PrintWriter pw = new PrintWriter(f)){
						            	  for(Statistics dr : list.getsList()){
						            		  pw.println(dr);
						              }
						              } catch(FileNotFoundException e1){
						            	  e1.printStackTrace();
						              }
									window.repaint();
								} catch(Exception e) {}
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
					for(int i=0; i < list.getsList().size(); i++){
						list.getHbeat().add(list.getsList().get(i).getHbeat());
						list.getTemp().add(list.getsList().get(i).getTemp());
						list.getHum().add(list.getsList().get(i).getTemp());
						list.getTime().add(list.getsList().get(i).getHour());
						
						}
					
					textDisplay.setText(" ");
					for(Statistics av : Utils.findAvPerHour(list.getHbeat(), list.getTime())){
						textDisplay.append("Average heart beat per minute:\t"+av.getAveragepHour() + "\n");
						//System.out.println(av.getAveragepHour() + "\n");
					}
					for(Statistics av : Utils.findAvPerHour(list.getTemp(), list.getTime())){
						textDisplay.append("Average temp per minute:\t"+av.getAveragepHour() + "\n");
						//System.out.println(av.getAveragepHour() + "\n");
					}
					for(Statistics av : Utils.findAvPerHour(list.getHum(), list.getTime())){
						textDisplay.append("Average humidity per minute"+av.getAveragepHour() + "\n");
						//System.out.println(av.getAveragepHour() + "\n");
					}
				}
			}
		});
		
		// show the window
		window.setVisible(true);
	}

}
