import java.text.DecimalFormat;

public abstract class Ticket {

	protected final double TAX = 1.096;
	protected final int TIME_NIGHT = 18;
	
	protected String type;
	protected String movieName;
	protected String rating;
	protected int day;
	protected int time;
	protected int id;
	protected double price;
	protected Format format;
	
	public Ticket() {
	    setType("");
	    setMovieName("");
	    setRating("");
	    setFormat(Format.NONE);
	    setDay(1);
	    setTime(8);
	    setPrice(-1.d);
	}
	
	  public Ticket(String movieName, String rating, int day, int time, String format) {
		    this.setType("");
		    this.setMovieName(movieName);
		    this.setRating(rating);
		    this.setDay(day);
		    this.setFormat(stringToFormat(format));
		    this.setTime(time);
		    this.setId(getId());
		    this.setPrice(-1.d);
	  }
	
	  public Ticket(String movieName, String rating, int day, int time, String format, int id, int moviesSeen) {
		    this.setType("");
		    this.setMovieName(movieName);
		    this.setRating(rating);
		    this.setDay(day);
		    this.setFormat(stringToFormat(format));
		    this.setTime(time);
		    this.setId(id);
		    this.setPrice(-1.d);
	  }
	  public Ticket(String movieName, String rating, int day, int time, String format, int id) {
		    this.setType("");
		    this.setMovieName(movieName);
		    this.setRating(rating);
		    this.setDay(day);
		    this.setFormat(stringToFormat(format));
		    this.setTime(time);
		    this.setId(id);
		    this.setPrice(-1.d);
	  } 
	  
	  public Ticket(String movieName, String rating, int day, int time, String type, String format, int id) {
		    this.setType(type);
		    this.setMovieName(movieName);
		    this.setRating(rating);
		    this.setDay(day);
		    this.setFormat(stringToFormat(format));
		    this.setTime(time);
		    this.setId(id);
		    this.setPrice(-1.d);
	  } 
	  
	  public abstract double calculateTicketPrice();
	  
	  public abstract int getId();
	  
	  public String toString() {
		  DecimalFormat df = new DecimalFormat("##0.00");
		  
		 String typeUpperCase = type.toUpperCase();
		 if (getId() != -1) {
			 typeUpperCase += ("-" + id);
		 }
		  
		 String viewingFormat = "";
		 switch(format) {
		 case THREE_D : 
			 viewingFormat = "3D";
			 break;
		 case IMAX :
			 viewingFormat = "IMAX";
			 break;
		 case NONE : 
			 viewingFormat = "";
			 break;
		 default:
			break;
		 }
		
		 return typeUpperCase + " " + viewingFormat + " Movie: " + movieName + " Rating: " + rating + " Day: " + day +
				 " Time: " + time + " Price: $" + df.format(price);
	  }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
	  
	public Format stringToFormat(String formatString) {
	    Format format;
	    if (formatString.equals("3D")) {
	    	format = Format.THREE_D;
	    } else if (formatString.equals("IMAX")) {
	    	format = Format.IMAX;
	    } else {
	    	format = Format.NONE;
	    }
	    return format;
	}
	
}
