package uk.ac.mmu.cnt;

import java.util.Scanner; //Scanner for Reading Input

public class Penny {

	public static void main(String[] args) {
		// Variables
		int penny[] = new int [8];
		penny[0]=1;
		penny[1]=2;
		penny[2]=20;
		penny[3]=50;
		penny[4]=100;
		penny[5]=200;
		penny[6]=500;
		penny[7]=1000;
		double vinput = 0;
		int count = 0;
		
		
		System.out.println("Enter the amount please");
		Scanner Scan = new Scanner (System.in);                                     //Scanner
		vinput = Scan.nextFloat();                                                  //Reading input
		vinput = vinput*100;                                                         // input is multiplied by 100 (converting Pound to penny)
		
		count=0;
		while(vinput>=penny[7])                                                      //using a while loop
		{
			vinput = vinput - penny[7];                                             //it will subtract the the input  
			count++;                                                              //counts the number of operations
			if(vinput<1000){                                                      //until it is less than 1000 penny
			System.out.println("You need"+"\t" + count+"\t" + "£10 note");         //prints the value of count             
			Scan.close();
			}
		}
		
		
		count=0;
		while(vinput>=penny[6] && vinput<penny[7])
		{
			vinput = vinput - penny[6];
			count++;
			if(vinput<500){
			System.out.println("You need"+"\t" + count+"\t" + "£5 note");
			Scan.close();
			}
		}
		
		count=0;
		while(vinput>=penny[5] && vinput<penny[6])
		{
			vinput = vinput - penny[5];
			count++;
			if(vinput<200){
			System.out.println("You need"+"\t" + count+"\t" + "£2");
			Scan.close();
			}
		}
		count=0;
		while(vinput>=penny[4] && vinput<penny[5])
		{
			vinput = vinput - penny[4];
			count++;
			if(vinput<100){
			System.out.println("You need"+"\t" + count+"\t" + "£1");
			Scan.close();
			}
		}
		count=0;
		while(vinput>=penny[3] && vinput < penny[4])
		{
			
			vinput = vinput - penny[3];
			count++;
			if(vinput<50){
			System.out.println("You need"+"\t" + count+"\t" + "50p");
			Scan.close();
			}
			
		}
		count = 0;
		while(vinput>=penny[2] && vinput < penny[3])
		{
			
			vinput = vinput - penny[2];
			count++;
			if(vinput<20){
			System.out.println("You need"+"\t" + count+"\t" + "20p");
			Scan.close();
			}
						
		}
		count = 0;
		while(vinput>=penny[1] && vinput < penny[2] )
		{
			
			vinput = vinput - penny[1];
			count++;
			if(vinput<penny[1]){
			System.out.println("You need"+"\t" + count+"\t" + "2p");
			Scan.close();
			}
						
		}
		count = 0;
		while(vinput>=penny[0] && vinput < penny[1] )
		{
			vinput = vinput - penny[0];
			count++;
			if(vinput<penny[0]){
			System.out.println("You need"+"\t" + count+"\t" + "1p");
			Scan.close();
			}
						
		}
		
		
	}

}
