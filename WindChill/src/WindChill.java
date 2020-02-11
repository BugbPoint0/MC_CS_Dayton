import java.util.Scanner; //imports Scanner to enable user input

public class WindChill
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		// declare variables
		Scanner userInput = new Scanner(System.in); // used to take user input
		double windSpeed; // holds the speed in MPH of the wind
		double temp; // holds the temperature in degrees fahrenheit
		double windChillTemp; // Holds the temperature in degrees fahrenheit after wind has been taken into account
		
		// Print header 
		System.out.print("Wind Chill Calculator");
		
		// Prompt and input for temp
		System.out.print("Enter the temperature in Fahrenheit (must be >= -45 and <= 40): ");
		temp = userInput.nextDouble();
		
		// Prompt and input for WindSpeed
		System.out.print("Enter the wind speed (must be >= 5 and <= 60): ");
		windSpeed = userInput.nextDouble();
		
		// Calculate wind chill
		windChillTemp = (35.74 + (0.6215 * temp) - (35.75 * Math.pow(windSpeed, 0.16)) + (0.4275 * temp * Math.pow(windSpeed, 0.16)));
		
		// print WindChillTemp
		System.out.print("\nWind chill temperature: " + windChillTemp + " degrees Fahrenheit\n" );
		
		// print programmer's name
		System.out.print("\nProgrammer: David Dayton");
		
		// close the scanner
		userInput.close(); 
		}
}	