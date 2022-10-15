package uk.ac.mmu.cnt2.arduino;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

//import com.sun.glass.ui.Pixels.Format;

import java.awt.TextArea;

import java.util.Date;



public class SerialCommsGUI extends JFrame implements SerialPortEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5164047937305246047L;
	private JPanel contentPane;
	Datalist list = new Datalist();
	private ArrayList<Double> celciusList = new ArrayList<Double>();  
	private ArrayList<Double> farenList = new ArrayList<Double>();  
	private ArrayList<Double> lightList = new ArrayList<Double>(); 
	private ArrayList<Integer> hourList = new ArrayList<Integer>();
	static String temp1;
	static String hbeat1;
	static String hum1;
	
	
 	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SerialCommsGUI frame = new SerialCommsGUI();
					frame.initialize();
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
	public SerialCommsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOn = new JButton("ON");
		btnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				        
	            try {
					output.write(1);
					System.out.println("on...");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnOn.setBounds(107, 11, 89, 23);
		contentPane.add(btnOn);
		
		JButton btnOff = new JButton("OFF");
		btnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					output.write(0);
					System.out.println("off...");
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnOff.setBounds(224, 11, 89, 23);
		contentPane.add(btnOff);
		
		textDisplay = new TextArea();
		textDisplay.setBounds(51, 51, 351, 201);
		contentPane.add(textDisplay);
		JButton btnNewButton = new JButton("Plot This");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				//open the plot thing here...
				SensorPlot chart = new SensorPlot("plot...", temp1, hbeat1, hum1);
				chart.setList(list.getsList());
				chart.pack();
				RefineryUtilities.centerFrameOnScreen(chart);
				chart.setVisible(true);
				//chart.repaint();
			
				
				
			}
		});
		btnNewButton.setBounds(181, 274, 89, 23);
		contentPane.add(btnNewButton);
		
	
	}

    
	/**
	 * This is where the SERIAL communication code begins...
	 * add things here...
	 */
	SerialPort serialPort;
    /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM4", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	private TextArea textDisplay;
	
	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		File f = new File("log.csv");
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				
				//get the current date time
				Date d = new Date();
				Statistics sensor = new Statistics();
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String datetime = dt.format(d);
				SimpleDateFormat hour = new SimpleDateFormat("S");
			    String hours = hour.format(new Date());
			   // hourList.add(Integer.parseInt(hours));
			    sensor.setHour(Integer.parseInt(hours));
				String inputLine=input.readLine(); // this is in CSV format of celcius,farenheit,light..
				// inputLine = input1;
				String[] incoming = inputLine.split(",");
				double hbeat = Double.parseDouble(incoming[0]); // this has the celsius reading...
				double temp = Double.parseDouble(incoming[1]); // this has the farenheit reading...
				double hum = Double.parseDouble(incoming[2]); //this is light
				hbeat1 = incoming[0];
				temp1 = incoming[1];
				hum1 = incoming[2];
				sensor.setHbeat(hbeat);
				sensor.setTemp(temp);
				sensor.setHum(hum);
				sensor.setDatetime(datetime);
				list.getsList().add(sensor);
				//farenList.add(temp);
							
				
              try(PrintWriter pw = new PrintWriter(f)){
            	  for(Statistics dr : list.getsList()){
            		  pw.println(dr);
              }
              } catch(FileNotFoundException e1){
            	  e1.printStackTrace();
              }
				//celciusList.add(hbeat);//a1dd this to the list for further use...
				//farenList.add(temp);
				//lightList.add(hum);
				// now i will plot the graphs in to line...
				System.out.println(datetime+" > "+inputLine); //print on the console for debug purpose...
				textDisplay.append(datetime+" > "+inputLine+"\n"); //display the value on to the textbox
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		
		// Ignore all the other eventTypes, but you should consider the other ones.
	}
}
