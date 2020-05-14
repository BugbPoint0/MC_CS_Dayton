
public class Adult extends Ticket {

	private final double DAY_PRICE = 10.5;
	private final double NIGHT_PRICE = 13.5;
	private final double IMAX_PRICE = 3;
	private final double THREE_D_PRICE = 2.5;
	
	public Adult() {
		super.type = "Adult";
	}
	
	 public Adult(String movieName, String rating, int day, int time, String format) {
		 super(movieName, rating, day, time, format);
		 super.type = "Adult";
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
		return -1;
	}

}
