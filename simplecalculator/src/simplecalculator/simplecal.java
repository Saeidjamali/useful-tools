package simplecalculator;

import java.util.Scanner;

public class simplecal {



public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

   Maths Maths = new Maths();

    double answer = 0;
    double inputA, inputB;
    char operator;
    boolean done = false;

     while (done == false) {
        System.out.print("Please enter your sum: ");

        inputA = input.nextDouble();
        operator = input.next().charAt(0);
        inputB = input.nextDouble();        

        switch (operator) {
            case '+': answer = Maths.add(inputA, inputB);
                      break;
            case '-': answer = Maths.subtract(inputA, inputB);
                      break;
            case '*': answer = Maths.multiply(inputA, inputB);
                      break;
            case '/': answer = Maths.divide(inputA, inputB);
                      break;
            case '^': answer = Maths.power(inputA, inputB);
                      break;
        }

            System.out.println(answer);             
    }       

    input.close();

  }

}
  class Maths {

    double add(double a, double b) {
        double answer = a+b;
        return answer;          
    }

    double subtract(double a, double b) {
        double answer = a-b;
        return answer;          
    }

    double multiply(double a, double b) {
        double answer = a*b;
        return answer;          
    }

    double divide(double a, double b) {
        double answer = a/b;
        return answer;          
    }

    double power(double a, double b){
        double answer =a;

        for (int x=2; x<=b; x++){
            answer *= a;
        }

        return answer;
    }

}