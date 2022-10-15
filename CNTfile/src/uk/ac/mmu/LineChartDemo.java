package uk.ac.mmu;

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
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class LineChartDemo extends ApplicationFrame
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static List<Sensor> list = new ArrayList<Sensor>();
	       static List<Integer> timelist= new ArrayList<Integer>();

	
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

	private static XYDataset createDataset()
	{

		XYSeries xyseries = new XYSeries("Temp");
		
		XYSeries xyseries1 = new XYSeries("Light");
		
		XYSeries xyseries2 = new XYSeries("Vcc");

		
		
		for(int i=0; i < list.size() && i<timelist.size(); i++){
		//	System.out.println(timelist);
			int time = timelist.get(i);
			//System.out.println(timelist.get(i));
			xyseries.add(time, Double.parseDouble(list.get(i).getTemp()));
			xyseries1.add(time, Double.parseDouble(list.get(i).getLight()));
			xyseries2.add(time, Double.parseDouble(list.get(i).getVcc()));
			 
		}

		
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
		xyseriescollection.addSeries(xyseries2);
		//xyseriescollection.addSeries(xyseries3);
		return xyseriescollection;
	}



	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Sensor readings", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
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

	public static void main(String args[])
	{
		LineChartDemo linechartdemo = new LineChartDemo("Line Chart Demonstration...");
		linechartdemo.pack();
		RefineryUtilities.centerFrameOnScreen(linechartdemo);
		linechartdemo.setVisible(true);
	}

	public List<Sensor> getList() {
	return list;
}
@SuppressWarnings("static-access")
public void setList(List<Sensor> list) {
	this.list = list;
}
public List<Integer> getTimelist() {
	return timelist;
}
@SuppressWarnings("static-access")
public void setTimelist(List<Integer> timelist) {
	this.timelist = timelist;
}
	
}