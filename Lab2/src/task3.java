import java.util.Scanner;
import java.util.Random;
public class task3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
boolean run = true;
while(run){
		int num = 0;
		int count = 0;
		String exit;
Random random= new Random();
Scanner input = new Scanner(System.in);
Scanner inputtext = new Scanner(System.in);

int rand = random.nextInt(100);
System.out.println(rand);
System.out.println("Enter a number: ");
num = input.nextInt();

		while (num != rand){
			if(num > rand){
			count++;
		System.out.println("The number you entered is greater than the random number. choose a smaller number");
		System.out.println("Number of tries: " + count);
		System.out.println("Enter a number: ");
		num = input.nextInt();
			}
		else if(num < rand){
			count++;
			System.out.println("The number you entered is smaller than the random number. choose a greater number");
			System.out.println("number of tries: " + count);
			System.out.println("Enter a number: ");
			num = input.nextInt();
		}
		}


	  if (rand == num){
		 count++;
		 System.out.println("You guessed right!");
		 System.out.println("Total number of tries: " + count);
		 System.out.println("would you like to play again. Enter Y or N");
		 exit = inputtext.nextLine();
		 if(exit.equalsIgnoreCase("y")){
			 run = true;
		 }
		 else if(exit.equalsIgnoreCase("n")){
			 run = false;
		 	}

	  	}

      }
	}

}