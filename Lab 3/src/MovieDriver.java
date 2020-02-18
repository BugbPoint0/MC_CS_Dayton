import java.util.Scanner; //imports Scanner to enable user input

public class MovieDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// declare variables
		Scanner userInput;
		Movie movie = new Movie();
		String movieTitle = new String();
		String movieRating = new String();
		int ticketsSold = 0;
		String yesOrNo = new String();
		
		do {
				
		userInput = new Scanner(System.in); // used to take user input
		
		//Prompt and input for movie title
		System.out.print("Enter a movie title please. ");
		movieTitle = userInput.nextLine();
		movie.setTitle(movieTitle);
		
		//Prompt and Input for the movie's ratings
		System.out.print("what is " + movie.getTitle() + "'s rating? ");
		movieRating = userInput.nextLine();
		movie.setRating(movieRating);
		
		//Prompt and Input for the movie's ratings
		System.out.print("How many tickets for " + movie.getTitle() + " sold at the theater? ");
		ticketsSold = userInput.nextInt();
		movie.setSoldTickets(ticketsSold);
		
		// print out all of the info
		System.out.print(movie.toString());
		
		// Prompt and input if they want to continue
		System.out.print("\nDo you want to enter another movie? (y or n). ");
		yesOrNo = userInput.next();
		
		
		// Check to see they do not want to continue
		} while (yesOrNo.contentEquals("y"));
		
		// Say good bye and thanks
		System.out.print("Thank You, Goodbye!");
		
		userInput.close();
		
	}

}
