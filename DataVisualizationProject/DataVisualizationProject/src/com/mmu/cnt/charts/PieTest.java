package com.mmu.cnt.charts;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

public class PieTest extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PieTest(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        // This will create the dataset 
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(650, 570));
        // add it to our application
        setContentPane(chartPanel);
    }
	
	 private  PieDataset createDataset() {
	        DefaultPieDataset result = new DefaultPieDataset();
	        result.setValue("Android", 84.4);
	        result.setValue("Apple", 11.7);
	        result.setValue("Windows", 2.9);
	        result.setValue("Blackberry", 0.5);
	        result.setValue("Others", 0.6);
	        return result;        
	    }
	 
	 	/**
	     * Creates a chart
	     */

	    private JFreeChart createChart(PieDataset dataset, String title) {
	        
	        JFreeChart chart = ChartFactory.createPieChart(title,          // chart title
	            dataset,                // data
	            true,                   // include legend
	            true,
	            false);

	        PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setStartAngle(290);
	        plot.setDirection(Rotation.ANTICLOCKWISE);
	        plot.setForegroundAlpha(0.5f);
	        return chart;
	        
	    }
	    
	    public static void main(String[] args) {
	    	PieTest demo = new PieTest("Comparison","Q3 2014: Worldwide Smartphone OS Market Share");
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);
	    }

}

