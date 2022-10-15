package com.test.json;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import org.jfree.ui.RefineryUtilities;
import org.omg.CORBA.Environment;

import com.google.gson.Gson;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GUIJSon extends JFrame {

	private JPanel contentPane;
	private TextArea textArea;
	List<Data> sList  = new ArrayList<Data>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIJSon frame = new GUIJSon();
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
	public GUIJSon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadJson = new JButton("Load JSON First");
		btnLoadJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//add the action for the button click.
				
				 Gson gsonRead = new Gson();
			        try {
						BufferedReader br = new BufferedReader(
								new FileReader(".\\weatherdata.json"));
						//convert the json string back to object
				    	DataList out = gsonRead.fromJson(br, DataList.class);
				    	
				    	
				    	sList = out.getsList();
				        
				        for(Data d: sList){
				        	textArea.append(d+"\n");
				        }   
			        
			        } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
		btnLoadJson.setBounds(116, 11, 130, 23);
		contentPane.add(btnLoadJson);
		
		textArea = new TextArea();
		textArea.setBounds(10, 111, 524, 238);
		contentPane.add(textArea);
		
		JButton btnPlotBar = new JButton("Plot Bar");
		btnPlotBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				System.out.println("plotting now");
				XYBarChartDemo xybarchartdemo1 = new XYBarChartDemo("Temperature Max");
				xybarchartdemo1.setsList(sList);
				xybarchartdemo1.create();
				xybarchartdemo1.pack();
				RefineryUtilities.centerFrameOnScreen(xybarchartdemo1);
				xybarchartdemo1.setVisible(true);
				
			}
		});
		btnPlotBar.setBounds(432, 11, 102, 23);
		contentPane.add(btnPlotBar);
		
		JButton btnPlotLine = new JButton("Plot Line");
		btnPlotLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("plotting now");
				LineChartDemo linechartdemo1 = new LineChartDemo("Temperatures");
				linechartdemo1.setsList(sList);
				linechartdemo1.create();
				linechartdemo1.pack();
				RefineryUtilities.centerFrameOnScreen(linechartdemo1);
				linechartdemo1.setVisible(true);
			}
		});
		btnPlotLine.setBounds(300, 11, 106, 23);
		contentPane.add(btnPlotLine);
	}
}
