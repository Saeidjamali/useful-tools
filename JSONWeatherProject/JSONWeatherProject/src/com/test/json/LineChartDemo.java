package com.test.json;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;


public class LineChartDemo extends JFrame
{
	
	private List<Data> sList  = new ArrayList<Data>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LineChartDemo(String s)
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
	
	private XYDataset createDataset()
	{
		XYSeries xyseries = new XYSeries("Maximum temperature");
		XYSeries xyseries1 = new XYSeries("Minimum temperature");
		int count=0;
		for(Data d: sList){			
			xyseries.add(++count, Double.parseDouble(d.getTempmax()));
			xyseries1.add(count, Double.parseDouble(d.getTempmin()));
		}
		
		//XYSeries xyseries2 = new XYSeries("Third");
				
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
		//xyseriescollection.addSeries(xyseries2);
		
		return xyseriescollection;
	}

	private JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Line Chart Demo", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
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

	public JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}
	
	public List<Data> getsList() {
		return sList;
	}

	public void setsList(List<Data> sList) {
		this.sList = sList;
		//System.out.println(sList);
	}
	
}