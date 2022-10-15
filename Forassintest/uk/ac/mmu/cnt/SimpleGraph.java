package uk.ac.mmu.cnt;



public class SimpleGraph {
	
	
	
	     
	// this is the constructor for our SimpleGraph program
	public SimpleGraph() {
		
		// create a new Graph object with labelled axes, a title, and one 
		// data sets
		Graph g1 = new Graph("Year", "Tmax", "Data for Max Temperature(degC)", 1);
		Graph g2 = new Graph("Year", "Tmin","Data for Min Temperature(degC)", 1);
		Graph g3 = new Graph("Year", "AF", "Data for AF (Days)", 1);
		Graph g4 = new Graph("Year", "Rain", "Data for Rain fall(mm)", 1);
		Graph g5 = new Graph("Year", "Sun", "Data for sunny hours(Hours)", 1);
	
		//using a for loop and the global arraylist to add the data from array list to the graph
		for(int i = 0; i < Main.records.size(); i++) {
			g1.add(Main.records.get(i).getYear(), Main.records.get(i).getTmax(), 1);
			g2.add(Main.records.get(i).getYear(), Main.records.get(i).getTmin(), 1);
			g3.add(Main.records.get(i).getYear(), Main.records.get(i).getafDays(), 1);
			g4.add(Main.records.get(i).getYear(), Main.records.get(i).getRain(), 1);
			g5.add(Main.records.get(i).getYear(), Main.records.get(i).getSun(), 1);
			}
		
 // display data 
	
		g1.showGraph();
		g2.showGraph();
		g3.showGraph();
		g4.showGraph();
		g5.showGraph();		
		}
	}