package uk.ac.mmu.cnt;

public class diceRoll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         int maxScore =13;
         int scorefrequencies[] = new int [maxScore];
         
         for(int i=0; i<100; i++) {
        	 
        	 int diceOne = ((int)(Math.random()*6)+1);
        	 int diceTwo = ((int)(Math.random()*6)+1);
        	 int score = diceOne+diceTwo;
        	 
        	 scorefrequencies[score] = scorefrequencies[score]+1;
         }
         
         System.out.println("table of score frequencies\n" + "---------\n\n" + "score\tTimes");
         
         for(int i = 2; i<maxScore; i++) {
        	 System.out.println(i+"\t"+scorefrequencies[i]);
         }
		
		
	}

}
