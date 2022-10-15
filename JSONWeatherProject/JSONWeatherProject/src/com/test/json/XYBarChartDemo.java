package com.test.json;

import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.*;
import org.jfree.data.xy.IntervalXYDataset;


public class XYBarChartDemo extends JFrame
{
	
	private List<Data> sList  = new ArrayList<Data>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XYBarChartDemo(String s)
	{
		super(s);
		
	}
	
	public void create()
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(650, 400));
		setContentPane(jpanel);
	}

	private JFreeChart createChart(IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYBarChart("Values", "Year", true, "Number", intervalxydataset, PlotOrientation.VERTICAL, true, false, false);
		jfreechart.addSubtitle(new TextTitle("Data display of temperature", new Font("Dialog", 2, 10)));
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
		StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
		xybarrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
		xybarrenderer.setMargin(0.10000000000000001D);
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		dateaxis.setLowerMargin(0.01D);
		dateaxis.setUpperMargin(0.01D);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	private IntervalXYDataset createDataset()
	{
		System.out.println("creating dataset"+this.sList);
		TimeSeries timeseries = new TimeSeries("Temp-max", "Year", "Count");
		try
		{
			int year=2000;//here for example i am setting year as 2000 and will increase this each time.			
			for(Data d: sList){
				timeseries.add(new Year(year++),Double.parseDouble(d.getTempmax()));				
			}			
		}
		catch (Exception exception)
		{
			System.err.println(exception.getMessage());
		}
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeseries);
		return timeseriescollection;
	}

	public JPanel createDemoPanel()
	{
		return new ChartPanel(createChart(createDataset()));
	}
	
	public List<Data> getsList() {
		return sList;
	}

	public void setsList(List<Data> sList) {
		this.sList = sList;
		//System.out.println(sList);
	}

//	public static void main(String args[])
//	{
//		XYBarChartDemo xybarchartdemo1 = new XYBarChartDemo("Demo");
//		xybarchartdemo1.pack();
//		RefineryUtilities.centerFrameOnScreen(xybarchartdemo1);
//		xybarchartdemo1.setVisible(true);
//	}
}