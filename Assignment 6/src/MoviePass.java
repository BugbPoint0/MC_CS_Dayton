
public class MoviePass extends Ticket {

	private final double DAY_PRICE = 10.5;
	private final double NIGHT_PRICE = 13.5;
	private final double IMAX_PRICE = 3;
	private final double THREE_D_PRICE = 2.5;
	private int moviesSeen;
	private int id;
	MovieTicketManager man = new MovieTicketManager();
	
	public MoviePass() {
		super.type = "MoviePass";
	}

	public MoviePass(String movieName, String rating, int day, int time, String format, int id, int moviesSeen) {
	    super(movieName, rating, day, time, format, id, moviesSeen);
		super.type = "MoviePass";
		this.moviesSeen = moviesSeen;
		this.id = id;

	}
	
	public MoviePass(String movieName, String rating, int day, int time, String format, int id) {
	    super(movieName, rating, day, time, format, id);
		super.type = "MoviePass";
		this.id = id;
	}
	
	public MoviePass(String movieName, String rating, int day, int time, String type, String format, int id) {
	    super(movieName, rating, day, time, type, format, id);
		super.type = "MoviePass";
		this.id = id;
	}
	
	@Override
	public double calculateTicketPrice() {
		double ticketPrice;
		
		
			
			if (time < TIME_NIGHT) {
				ticketPrice = DAY_PRICE;
			} else {
				ticketPrice = NIGHT_PRICE;
			}
			
			switch(format) {
			 case THREE_D : 
				 ticketPrice += THREE_D_PRICE;
				 break;
			 case IMAX :
				 ticketPrice += IMAX_PRICE;
				 break;
			 default:
				break;
			 }
			
			ticketPrice *= TAX;
		
			return ticketPrice;
	}

	@Override
	public int getId() {
		return id;
	}

}
