package ac.uk.muu;



public class BMILogic {
	static double result;
public static String calculate (double weight, double height, String gender){
		
		result = (weight/ (height*height));
		 String bmiresult ="";
	     
		// TODO Auto-generated method stub
		
	
	
		
		
		if(gender=="Male" ){
			if(result < 20.7 ){
				bmiresult ="Underweight";
			}
			else if(result >= 20.7 && result <26.4){
				bmiresult ="In Normal Range";
			}
			else if (result >= 26.4 && result < 27.8){
				bmiresult ="Marginally Overweight";
			}
			else if (result >= 27.8 && result < 31.1){
				bmiresult ="Overweight";
			}
			else if (result > 31.1){
				bmiresult ="Obese";
			}	
		}
		if(gender == "Female") {
			if(result < 19.1 ){
				bmiresult ="Underweight";
			}
			else if(result >= 19.1 && result <25.8){
				bmiresult ="In Normal Range";
			}
			else if (result >= 25.8 && result < 27.3){
				bmiresult ="Marginally Overweight";
			}
			else if (result >= 27.3 && result < 32.3){
				bmiresult ="Overweight";
			}
			else if (result > 32.3){
				bmiresult ="Obese";
			}
			
		}
		
		return bmiresult;
	}
	
}
