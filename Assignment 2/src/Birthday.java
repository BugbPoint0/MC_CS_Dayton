/** A driver class for toy 
* Assignment 2 
* David Dayton 
* 2/24/20 */
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
public class Birthday {

	public static void main(String[] args)
	{
		// declare and initialize variables
		Toy birthDayToy = new Toy(); // toy object 
		String name = new String(); // holds the child's name 
		String age = new String(); // holds the child's age while it is a string so it can get parsed into an integer
		int cancel = -1; // a loop variable for user input
		String repeat = new String(); // a loop variable for repeating the whole toy request
		int orderNumber; // holds a random number that will be displayed as the order number
		double cost = 0; // holds the total amount
		DecimalFormat dollar = new DecimalFormat("#,##0.00");	//format cost
		
		// print title 
		System.out.print("BIRTHDAY GIFTS\n\n");
		
		do {
		do {
			// reset the loop variable 	
		cancel = -1; 
		
		// Display welcome message 
		JOptionPane.showMessageDialog(null, "Welcome to the Toy Company. Choose gifts for young children!");
		
		// Prompt and input for child's name
		name = JOptionPane.showInputDialog("Enter the child's name.");
		
		// Prompt and input for the child's age
		age = JOptionPane.showInputDialog("How old is the child?");
		int i = Integer.parseInt(age); // parse the age of the child into an integer
		birthDayToy.setAge(i);
		
		// Prompt and input for toy choice 
		birthDayToy.setToy(JOptionPane.showInputDialog("Choose a toy: a plushie, blocks, or a book"));
		if (!birthDayToy.ageOK()) // user validation for if the toy they chose was age appropriate 
		{
			// does the user want to continue or cancel the toy request?
			cancel = JOptionPane.showConfirmDialog(null, "The toy is not age appropriate, do you want to cancel the toy request?");
		}
		
		} while (cancel == 0); 
		
		// Determine the cost of the toy 
		birthDayToy.setCost(birthDayToy.getToy());
		
		// prompt and input for if they want a card and balloon
		birthDayToy.addCard(JOptionPane.showInputDialog(null, "Do you want a card with the gift? Yes or No"));
		birthDayToy.addBalloon(JOptionPane.showInputDialog(null, "Do you want a balloon with the gift? Yes or No"));
		
		// Display on console the name, age, and total for the gift
		System.out.println("The gift for " + name + birthDayToy.toString());
		
		// prompt and input for if they want another toy
		repeat = JOptionPane.showInputDialog("Do you want another toy? Yes or No?");
		
		cost += birthDayToy.getCost(); // add the cost of the purchase to the total cost
		
		} while (repeat.equalsIgnoreCase("yes"));
		
		orderNumber = 10000000 + new Random().nextInt(90000000); // generate order number
		
		// display total cost and order number 
		System.out.print("The total cost of your order is $" + dollar.format(cost) + " Order number is " + orderNumber); 
		
		// Display programmers name
		System.out.print("\nProgrammer: David Dayton");
	}
}
