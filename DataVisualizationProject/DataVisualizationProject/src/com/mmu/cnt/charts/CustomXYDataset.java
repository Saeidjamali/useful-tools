package com.mmu.cnt.charts;


import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;

public class CustomXYDataset extends AbstractXYDataset
	implements XYDataset
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double translate;

	public CustomXYDataset()
	{
		translate = 0.0D;
	}

	public double getTranslate()
	{
		return translate;
	}

	public void setTranslate(double d)
	{
		translate = d;
		notifyListeners(new DatasetChangeEvent(this, this));
	}

	public Number getX(int i, int j)
	{
		return new Double(-10D + translate + (double)j / 10D);
	}

	public Number getY(int i, int j)
	{
		if (i == 0)
			return new Double(Math.cos(-10D + translate + (double)j / 10D));
		else if(i == 1)
			return new Double(Math.sin(-10D + translate + (double)j / 10D));
		else
			return new Double(2D * Math.sin(-10D + translate + (double)j / 10D));
	}

	public int getSeriesCount()
	{
		return 3;
	}

	public Comparable getSeriesKey(int i)
	{
		if (i == 0)
			return "y = cosine(x)";
		else if (i == 1)
			return "y = sine(x)";
		else if (i == 2)
			return "y = 2*sine(x)";
		else
			return "Error";
	}

	public int getItemCount(int i)
	{
		return 200;
	}
}