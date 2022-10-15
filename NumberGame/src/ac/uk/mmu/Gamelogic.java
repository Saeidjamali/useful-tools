package ac.uk.mmu;

import java.util.Random;


public class Gamelogic {
	static int count = 0;
	public static String calculate (int num) {
	boolean run = true;
	String result="";
	
		
	
	

			
				if(num > GameGUI.rand){
				count++;
			result="Greater.choose a smaller number";
			//System.out.println("Number of tries: " + count);
			//System.out.println("Enter a number: ");
			//num = input.nextInt();
				}
			else if(num < GameGUI.rand){
				count++;
				result="Smaller.choose a greater number";
				//System.out.println("number of tries: " + count);
			//	System.out.println("Enter a number: ");
				//num = input.nextInt();
			}
			


			else if(GameGUI.rand == num){
			 count++;
			 result="You guessed right!";
			 //System.out.println("Total number of tries: " + count);
			

		  }	  	
	
	return result;
 }
}
