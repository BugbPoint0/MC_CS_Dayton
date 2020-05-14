
public class Employee extends Ticket {

	private final double DAY_PRICE = 5.25;
	private final double NIGHT_PRICE = 6.75;
	private final double IMAX_PRICE = 1.5;
	private final double THREE_D_PRICE = 1.25;
	private int moviesSeen;
	private int id;
	
	public Employee() {
		super.type = "Employee";
	}

	public Employee(String movieName, String rating, int day, int time, String format, int id, int moviesSeen) {
	    super(movieName, rating, day, time, format, id, moviesSeen);
		super.type = "Employee";
		this.moviesSeen = moviesSeen;
		this.id = id;
	}
	
	public Employee(String movieName, String rating, int day, int time, String format, int id) {
	    super(movieName, rating, day, time, format, id);
		super.type = "Employee";
		this.id = id;
	}
	
	public Employee(String movieName, String rating, int day, int time, String type, String format, int id) {
	    super(movieName, rating, day, time, type, format, id);
		super.type = "Employee";
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
		
		if (moviesSeen <= 1) {
			ticketPrice = 0.d;
		}
		return ticketPrice;
	}

	@Override
	public int getId() {
		return id;
	}

}
