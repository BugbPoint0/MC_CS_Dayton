import java.text.DecimalFormat;

public class Child extends Ticket {

	private final double DAY_PRICE = 5.75;
	private final double NIGHT_PRICE = 10.75;
	private final double IMAX_PRICE = 2;
	private final double THREE_D_PRICE = 1.5;
	
	 public Child() {
		 super.type = "Child";
	 }

	 public Child(String movieName, String rating, int day, int time, String format) {
		 super(movieName, rating, day, time, format);
		 super.type = "Child";
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
