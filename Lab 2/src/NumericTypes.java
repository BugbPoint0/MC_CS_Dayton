/**
   This program demonstrates how numeric types and operators behave in Java
*/
import java.util.Scanner;  // Import the Scanner class
public class NumericTypes {
	public static void main (String [] args) {
		//TASK #2 Create a Scanner object here 
		//identifier declarations
		final double NUMBER = 2.0; // number of scores
		int score1 = 100; // first test score
		int score2 = 95; // second test score
		final int BOILING_IN_F = 212; // boiling temperature
		double fToC; // temperature in Celsius
		double average; // arithmetic average
		String output; // line of output to print out
		
		Scanner userInput = new Scanner(System.in); // initalize a scanner object
		double tempInFahrenheit; // holds the temperature in fahrenheit that the user inputs 
		System.out.print("Input your first score. "); // prompt score 1
		score1 = userInput.nextInt(); // input for score 1
		System.out.print("Input your second score. "); // prompt score 2
		score2 = userInput.nextInt(); // input for score 2
		//Task #2 declare a variable to hold the user’s temperature
		//Task #2 prompt user to input score1
		//Task #2 read score1 
		//Task #2 prompt user to input score2
		//Task #2 read score2 
		// Find an arithmetic average
		
		average = (score1 + score2) / NUMBER;
		output = score1 + " and " + score2 + " have an average of " + average;	
		System.out.println(output);
		// Convert Fahrenheit temperatures to Celsius
		fToC = (5.0/9.0) * (BOILING_IN_F - 32);
		output = BOILING_IN_F + " in Fahrenheit is " + fToC + " in Celsius.";
		System.out.println(output);
		
		System.out.print("Input another temperature in Fahrenheit. "); // prompt input for temp
		tempInFahrenheit = userInput.nextDouble(); // input for temp
		System.out.print("You entered " + tempInFahrenheit);
		fToC = (5.0/9.0) * (tempInFahrenheit - 32);
		System.out.print("\n" + tempInFahrenheit + " in Fahrenheit is " + fToC + " in Celsius.");
		//Task #2 prompt user to input another temperature
		//Task #2 read the user’s temperature in Fahrenheit
		//Task #2 convert the user’s temperature to Celsius
		//Task #2 print the user’s temperature in Celsius
		
		System.out.println("Goodbye"); // to show that the program is ended	
		userInput.close(); // Closes the scanner
	}
}
