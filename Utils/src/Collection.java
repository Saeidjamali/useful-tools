import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Double> source = new ArrayList<Double>();
        List<Double> destination = new ArrayList<Double>();
        
        source.add(1.0);
        source.add(2.0);
        source.add(3.0);
        
       // Collections.copy(destination, source);
        for(int i=0; i < source.size(); i++){
        	destination.add(source.get(i));
        	System.out.println(destination.get(i));
        }
	}

}
