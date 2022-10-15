package uk.ac.mmu.cnt2.arduino;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;

import gnu.io.CommPort;
import gnu.io.SerialPortEvent;


public class SensorPlot extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   // private static ArrayList<Double> temp;
    //private static ArrayList<Double> hbeat;
    //private static ArrayList<Double> hum;
     static List<Statistics> list = new ArrayList<Statistics>();
     static String temp;
      static String hbeat;
     static String hum;


	public SensorPlot(String s,String temp1,String hbeat1,String hum1)
	{	
		super(s);		
		this.temp = temp1;
		this.hbeat=hbeat1;
		this.hum = hum1;
		//temp1 = temp;
		//hbeat1 = hbeat;
		//hum1 = hum;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
		jpanel.repaint();
	}

	
	private static XYDataset createDataset()
	{
		//create dataset here
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
	   	XYSeries xyseries = new XYSeries("temp");
		XYSeries xyseries1 = new XYSeries("hbeat");
		XYSeries xyseries2 = new XYSeries("hum");
		int time =0;
		
		
		/*xyseries.add(i, Double.parseDouble(temp));
		xyseries1.add(i, Double.parseDouble(hbeat));
		xyseries2.add(i, Double.parseDouble(hum));*/
		
   		for(int i=0; i< list.size(); i++){
			time = list.get(i).getHour();
			xyseries.add(i, list.get(i).getTemp());
			xyseries1.add(i, list.get(i).getHbeat());
			xyseries2.add(i, list.get(i).getHum());
			
   		
		}
		
		
	/*	int i=1;
		for(Double temp:temp){
			//System.out.println(i+" "+temp);
			xyseries.add(i,temp);
			++i;
		}
		i=1;
		for(Double temp:hbeat){
			//System.out.println(i+" "+temp);
			xyseries1.add(i,temp);
			++i;
		}
		i=2;
		for(Double temp:hum){
			//System.out.println(i+" "+temp);
			xyseries2.add(i,temp);
			++i;
		}*/
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
		xyseriescollection.addSeries(xyseries2);
		return xyseriescollection;
		
		
	}




	public static List<Statistics> getList() {
		return list;
	}
	
	@SuppressWarnings("static-access")
	public void setList(List<Statistics> list) {
		this.list = list;
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Temperature Plots...", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseShapesFilled(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}



}