package com.mmu.cnt.charts;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DialDemoTest extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static class DemoPanel extends JPanel
		implements ChangeListener
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JSlider slider;
		DefaultValueDataset dataset;

		public static JFreeChart createStandardDialChart(String s, String s1, ValueDataset valuedataset, double d, double d1, double d2, int i)
		{
			DialPlot dialplot = new DialPlot();
			dialplot.setDataset(valuedataset);
			dialplot.setDialFrame(new StandardDialFrame());
			dialplot.setBackground(new DialBackground());
			DialTextAnnotation dialtextannotation = new DialTextAnnotation(s1);
			dialtextannotation.setFont(new Font("Dialog", 1, 14));
			dialtextannotation.setRadius(0.69999999999999996D);
			dialplot.addLayer(dialtextannotation);
			DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
			dialplot.addLayer(dialvalueindicator);
			StandardDialScale standarddialscale = new StandardDialScale(d, d1, -120D, -300D, 10D, 4);
			standarddialscale.setMajorTickIncrement(d2);
			standarddialscale.setMinorTickCount(i);
			standarddialscale.setTickRadius(0.88D);
			standarddialscale.setTickLabelOffset(0.14999999999999999D);
			standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
			dialplot.addScale(0, standarddialscale);
			dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
			DialCap dialcap = new DialCap();
			dialplot.setCap(dialcap);
			return new JFreeChart(s, dialplot);
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset.setValue(new Integer(slider.getValue()));
		}

		public DemoPanel()
		{
			super(new BorderLayout());
			dataset = new DefaultValueDataset(10D);
			JFreeChart jfreechart = createStandardDialChart("Dial Demo", "Temperature", dataset, -40D, 60D, 10D, 4);
			DialPlot dialplot = (DialPlot)jfreechart.getPlot();
			StandardDialRange standarddialrange = new StandardDialRange(40D, 60D, Color.red);
			standarddialrange.setInnerRadius(0.52000000000000002D);
			standarddialrange.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange);
			StandardDialRange standarddialrange1 = new StandardDialRange(10D, 40D, Color.orange);
			standarddialrange1.setInnerRadius(0.52000000000000002D);
			standarddialrange1.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange1);
			StandardDialRange standarddialrange2 = new StandardDialRange(-40D, 10D, Color.green);
			standarddialrange2.setInnerRadius(0.52000000000000002D);
			standarddialrange2.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange2);
			GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
			DialBackground dialbackground = new DialBackground(gradientpaint);
			dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
			dialplot.setBackground(dialbackground);
			dialplot.removePointer(0);
			org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
			dialplot.addPointer(pointer);
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(400, 400));
			slider = new JSlider(-40, 60);
			slider.setMajorTickSpacing(10);
			slider.setPaintLabels(true);
			slider.addChangeListener(this);
			add(chartpanel);
			add(slider, "South");
		}
	}


	public DialDemoTest(String s)
	{
		super(s);
		setDefaultCloseOperation(3);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new DemoPanel();
	}

	public static void main(String args[])
	{
		DialDemoTest dialdemo = new DialDemoTest("Dial Demo...");
		dialdemo.pack();
		dialdemo.setVisible(true);
	}
}