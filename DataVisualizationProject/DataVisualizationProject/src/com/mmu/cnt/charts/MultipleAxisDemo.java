package com.mmu.cnt.charts;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class MultipleAxisDemo extends ApplicationFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleAxisDemo(String s)
	{
		super(s);
		ChartPanel chartpanel = (ChartPanel)createDemoPanel();
		chartpanel.setPreferredSize(new Dimension(850, 540));
		chartpanel.setDomainZoomable(true);
		chartpanel.setRangeZoomable(true);
		setContentPane(chartpanel);
	}

	private static JFreeChart createChart()
	{
		XYDataset xydataset = createDataset("Series 1", 100D, new Minute(), 200);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Multiple Axis Demo", "Time of Day", "Primary Range Axis", xydataset, true, true, false);
		jfreechart.addSubtitle(new TextTitle("Four datasets and four range axes."));
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setOrientation(PlotOrientation.VERTICAL);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		xyplot.getRangeAxis().setFixedDimension(15D);
		NumberAxis numberaxis = new NumberAxis("Range Axis 2");
		numberaxis.setFixedDimension(10D);
		numberaxis.setAutoRangeIncludesZero(false);
		xyplot.setRangeAxis(1, numberaxis);
		xyplot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
		XYDataset xydataset1 = createDataset("Series 2", 1000D, new Minute(), 170);
		xyplot.setDataset(1, xydataset1);
		xyplot.mapDatasetToRangeAxis(1, 1);
		StandardXYItemRenderer standardxyitemrenderer = new StandardXYItemRenderer();
		xyplot.setRenderer(1, standardxyitemrenderer);
		NumberAxis numberaxis1 = new NumberAxis("Range Axis 3");
		xyplot.setRangeAxis(2, numberaxis1);
		XYDataset xydataset2 = createDataset("Series 3", 10000D, new Minute(), 170);
		xyplot.setDataset(2, xydataset2);
		xyplot.mapDatasetToRangeAxis(2, 2);
		StandardXYItemRenderer standardxyitemrenderer1 = new StandardXYItemRenderer();
		xyplot.setRenderer(2, standardxyitemrenderer1);
		NumberAxis numberaxis2 = new NumberAxis("Range Axis 4");
		xyplot.setRangeAxis(3, numberaxis2);
		XYDataset xydataset3 = createDataset("Series 4", 25D, new Minute(), 200);
		xyplot.setDataset(3, xydataset3);
		xyplot.mapDatasetToRangeAxis(3, 3);
		StandardXYItemRenderer standardxyitemrenderer2 = new StandardXYItemRenderer();
		xyplot.setRenderer(3, standardxyitemrenderer2);
		ChartUtilities.applyCurrentTheme(jfreechart);
		xyplot.getRenderer().setSeriesPaint(0, Color.black);
		standardxyitemrenderer.setSeriesPaint(0, Color.red);
		numberaxis.setLabelPaint(Color.red);
		numberaxis.setTickLabelPaint(Color.red);
		standardxyitemrenderer1.setSeriesPaint(0, Color.blue);
		numberaxis1.setLabelPaint(Color.blue);
		numberaxis1.setTickLabelPaint(Color.blue);
		standardxyitemrenderer2.setSeriesPaint(0, Color.green);
		numberaxis2.setLabelPaint(Color.green);
		numberaxis2.setTickLabelPaint(Color.green);
		return jfreechart;
	}

	private static XYDataset createDataset(String s, double d, RegularTimePeriod regulartimeperiod, int i)
	{
		TimeSeries timeseries = new TimeSeries(s);
		RegularTimePeriod regulartimeperiod1 = regulartimeperiod;
		double d1 = d;
		for (int j = 0; j < i; j++)
		{
			timeseries.add(regulartimeperiod1, d1);
			regulartimeperiod1 = regulartimeperiod1.next();
			d1 *= 1.0D + (Math.random() - 0.495D) / 10D;
		}

		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		MultipleAxisDemo multipleaxisdemo = new MultipleAxisDemo("Multiple Axis Demo...");
		multipleaxisdemo.pack();
		RefineryUtilities.centerFrameOnScreen(multipleaxisdemo);
		multipleaxisdemo.setVisible(true);
	}
}