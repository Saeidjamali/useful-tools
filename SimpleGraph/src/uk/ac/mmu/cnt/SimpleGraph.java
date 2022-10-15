package uk.ac.mmu.cnt;

public class SimpleGraph {

	// declare our data for the x axis
	double[] xdata = {0.0, 0.25, 0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 
			2.25, 2.5, 2.75, 3.0, 3.25, 3.5, 3.75, 4.0, 4.25, 4.5, 4.75,
			5.0, 5.25, 5.5, 5.75, 6.0, 6.25, 6.5, 6.75, 7.0};
	
	// this is the constructor for our SimpleGraph program
	public SimpleGraph() {
		
		// create a new Graph object with labelled axes, a title, and two 
		// data sets
		Graph g = new Graph("x", "y", "sin(x) / cos(x)", 2);
		
		// add the data to the graph (here we are adding two data sets - 
		// sin(x) and cos(x)
		for(int i = 0; i < xdata.length; i++) {
			g.add(xdata[i], Math.sin(xdata[i]), 1);
			g.add(xdata[i], Math.cos(xdata[i]), 2);
		}
		
		// display the graph
		g.showGraph();
		
	}
	
	// this is out main method where we run our program
	public static void main(String[] args) {
		new SimpleGraph();
	}
	
}



















