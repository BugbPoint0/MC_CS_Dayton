import java.util.Scanner;  // Import the Scanner class

public class SphereVolume {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double radius; // holds the radius of the sphere
		double diam; // holds the diameter of the sphere
		double volume; // holds the volume of the sphere
		Scanner userInput = new Scanner(System.in); // allows user input
		
		// print header
		System.out.print("This is a sphere volume calculator!\n\n");
		
		//prompt and input diam
		System.out.print("Please enter the diameter of the sphere ");
		diam = userInput.nextDouble();
		
		// print diam
		System.out.print("The sphere's diameter is " + diam + "\n");
		
		// calculate radius and volume
		radius = diam / 2.0;
		volume = (4.0/3.0) * Math.PI * Math.pow(radius, 3.0);
		
		// print the volume of the sphere
		System.out.print("The sphere's volume is " + volume);
		
		userInput.close(); // closes the scanner object
	}

}
