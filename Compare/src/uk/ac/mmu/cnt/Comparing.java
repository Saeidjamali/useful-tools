package uk.ac.mmu.cnt;

import java.util.ArrayList;
import java.util.Scanner;

public class Comparing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  Scanner scan = new Scanner (System.in);
	  String mychar = scan.next();  
	  char c = mychar.charAt(0);
	
		//for(int i=0; i< 200;i++){
			if((c >=95 && c <=122) || (c>=65 && c<=65)){
			System.out.println(c);
			}
			else{
				System.out.println("Ascii character not recognized");
			}
		}
     
	}


