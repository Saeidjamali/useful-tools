import java.util.Scanner;
public class task2 {

	static double result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Scanner textscan = new Scanner(System.in);
		double weight;
		double height;
		String gender;
	
		
		System.out.println("Please enter your weight in Kilogram");
		weight = input.nextDouble();
		System.out.println("Please enter your height in meter");
		height = input.nextDouble();
		System.out.println("Please Enter your geder. male or female");
		gender = textscan.nextLine();
		
		health(weight, height);
		if(gender.equalsIgnoreCase("male") ){
			if(result < 20.7 ){
				System.out.println("Underweight");
			}
			else if(result >= 20.7 && result <26.4){
				System.out.println("In Normal Range");
			}
			else if (result >= 26.4 && result < 27.8){
				System.out.println("Marginally Overweight");
			}
			else if (result >= 27.8 && result < 31.1){
				System.out.println("Overweight");
			}
			else if (result > 31.1){
				System.out.println("Obese");
			}	
		}
		if(gender.equalsIgnoreCase("female") ){
			if(result < 19.1 ){
				System.out.println("Underweight");
			}
			else if(result >= 19.1 && result <25.8){
				System.out.println("In Normal Range");
			}
			else if (result >= 25.8 && result < 27.3){
				System.out.println("Marginally Overweight");
			}
			else if (result >= 27.3 && result < 32.3){
				System.out.println("Overweight");
			}
			else if (result > 32.3){
				System.out.println("Obese");
			}
			
		}
		else{
			System.out.println("Please enter male or female");
		}
		
	}
	
	public static double health (double weight, double height){
		
		result = (weight/ (height*height));
		 return result;
	}

}
