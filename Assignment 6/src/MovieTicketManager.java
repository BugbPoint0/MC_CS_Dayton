import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieTicketManager implements MovieTicketManagerInterface {

	private ArrayList<Ticket> ticketList;
	private DecimalFormat currencyFormat = new DecimalFormat("##0.00");
	
	
	MovieTicketManager() {
		ticketList = new ArrayList<>();
	}
	
	@Override
	public int numVisits(int id) {
		int visitCount = 0;
		for (int index = 0; index < ticketList.size(); index++) {
			if (ticketList.get(index).getId() == id) {
				visitCount++;
			}
		}
		return visitCount;
	}

	@Override
	public int numThisMovie(int id, String movie) {
		int movieCount = 0;
		for (int index = 0; index < ticketList.size(); index++) {
			if ((ticketList.get(index).getId() == id) && (ticketList.get(index).getMovieName() == movie)) {
				movieCount++;
			}
		}
		return movieCount;
	}

	@Override
	public int numMoviesToday(int id, int date) {
		int movieCount = 0;
		for (int index = 0; index < ticketList.size(); index++) {
			if ((ticketList.get(index).getId() == id) && (ticketList.get(index).getDay() == date)) {
				movieCount++;
			}
		}
		return movieCount;
	}

	@Override
	public double addTicket(String movieName, String rating, int day, int time, String format, String type, int id) {
		
		double ticketPrice = -1;
		
	    switch (type) {
	      case "Adult":
	    	  Adult ticketAdult = new Adult(movieName, rating, day, time, format);
	    	  ticketAdult.setPrice(ticketAdult.calculateTicketPrice());
	    	  ticketList.add(ticketAdult);
	    	  ticketPrice = ticketAdult.calculateTicketPrice();
	    	  break;
	      case "Child":
	    	  Child ticketChild = new Child(movieName, rating, day, time, format);
	    	  ticketChild.setPrice(ticketChild.calculateTicketPrice());
	    	  ticketList.add(ticketChild);
	    	  ticketPrice = ticketChild.calculateTicketPrice();
	    	  break;
	      case "Employee":
	    	  Employee ticketEmployee = new Employee(movieName, rating, day, time, type, format, id);
	    	  if (numVisits(id) < 2) {
	    		  ticketEmployee.setPrice(0);
	    		  ticketList.add(ticketEmployee);
	    		  ticketPrice = 0;
	    	  } else {
	    		  ticketEmployee.setPrice(ticketEmployee.calculateTicketPrice());
	    		  ticketList.add(ticketEmployee);
	    		  ticketPrice = ticketEmployee.calculateTicketPrice();
	    	  }
	    	  break;
	      case "MoviePass":
	    	  MoviePass ticketMoviePass = new MoviePass(movieName, rating, day, time, type, format, id);
	    	  if ((numMoviesToday(id, day) == 0) && (ticketMoviePass.getFormat() == Format.NONE) 
	        		&& (numThisMovie(id, movieName) == 0)) {
	    		  if (numVisits(id) == 0) {
	    			  ticketMoviePass.setPrice(9.99);
	    			  ticketList.add(ticketMoviePass);
	    			  ticketPrice = 9.99;
	    		  } else {
	    			  ticketMoviePass.setPrice(0);
		    		  ticketList.add(ticketMoviePass);
		    		  ticketPrice = 0;
	    		  }
	    		
	    		  } else { 
	    			  ticketMoviePass.setPrice(ticketMoviePass.calculateTicketPrice());
	    			  ticketList.add(ticketMoviePass);
	    			  ticketPrice = ticketMoviePass.calculateTicketPrice();
	    		  }
	    	  break;
	      default:
	    	  break;
	    }
	    return ticketPrice;
	}


	@Override
	public double totalSalesMonth() {
		double totalSales = 0.d;
		for (int index = 0; index < ticketList.size(); index++) {
			totalSales += ticketList.get(index).getPrice();
		}
		return totalSales;
	}

	@Override
	public String monthlySalesReport() {
		
		int adultTickets = 0;
		double adultTicketSales = 0.d;
		int childTickets = 0;
		double childTicketSales = 0.d;
		int employeeTickets = 0;
		double employeeTicketSales = 0.d;
		int moviePassTickets = 0;
		double moviePassTicketSales = 0.d;
		double totalSales = 0.d;
	
		for (int index = 0; index < ticketList.size(); index++) {
			if (ticketList.get(index).getType() == "Adult") {
				adultTickets++;
				adultTicketSales += ticketList.get(index).getPrice();
			}
		}
		
		for (int index = 0; index < ticketList.size(); index++) {
			if (ticketList.get(index).getType() == "Child") {
				childTickets++;
				childTicketSales += ticketList.get(index).getPrice();
			}
		}
		
		for (int index = 0; index < ticketList.size(); index++) {
			if (ticketList.get(index).getType() == "Employee") {
				employeeTickets++;
				employeeTicketSales += ticketList.get(index).getPrice();
			}
		}
		
		for (int index = 0; index < ticketList.size(); index++) {
			if (ticketList.get(index).getType() == "MoviePass") {
				moviePassTickets++;
				moviePassTicketSales += ticketList.get(index).getPrice();
			}
		}
		
		for (int index = 0; index < ticketList.size(); index++) {
				totalSales += ticketList.get(index).getPrice();			
		}
		
		return "\tMonthly Sales Report\n\n \t\t\tSales\tNumber\n"
				+ "ADULT\t\t$" + currencyFormat.format(adultTicketSales)
				+ "\t\t" + adultTickets + "\nCHILD\t\t$" + currencyFormat.format(childTicketSales)
				+ "\t\t" + childTickets + "\nEMPLOYEE\t$" + currencyFormat.format(employeeTicketSales)
				+ "\t\t" + employeeTickets + "\nMOVIEPASS\t$" + currencyFormat.format(moviePassTicketSales)
				+ "\t\t" + moviePassTickets + "\n\nTotal Monthly Sales $" + currencyFormat.format(totalSales)
				+ "\n";
	}

	@Override
	public ArrayList<String> get3DTickets() {
		sortByDay();
		ArrayList<String> threeDList = new ArrayList<>();
		String ticketInfo = new String();
		
		for (int index = 0; index < ticketList.size(); index++) {
			if(ticketList.get(index).getFormat() == Format.THREE_D) {
				ticketInfo = ticketList.get(index).toString();
				threeDList.add(ticketInfo);
			}	
		}
		return threeDList;
	}

	@Override
	public ArrayList<String> getAllTickets() {
		sortByDay();
		ArrayList<String> allTicketList = new ArrayList<>();
		String ticketInfo = new String();
		
		for (int index = 0; index < ticketList.size(); index++) {
				ticketInfo = ticketList.get(index).toString();
				allTicketList.add(ticketInfo);
		}
		return allTicketList;
	}

	@Override
	public ArrayList<String> getMoviePassTickets() {
		sortById();
		ArrayList<String> moviePassList = new ArrayList<>();
		String ticketInfo = new String();
		
		for (int index = 0; index < ticketList.size(); index++) {
			if(ticketList.get(index).getType() == "MoviePass") {
				ticketInfo = ticketList.get(index).toString();
				moviePassList.add(ticketInfo);
			}
		}
		return moviePassList;
	}

	@Override
	public void readFile(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		
	    while (scan.hasNextLine()) {
	      String [] string = scan.nextLine().split(":");
	      addTicket(string[0], string[1], Integer.parseInt(string[2]), Integer.parseInt(string[3]), string[4], string[5], 
	    		  Integer.parseInt(string[6]));
	    }

	    scan.close();
	}

	private void sortByDay() {
		Ticket temp;
		for (int i = 1; i < ticketList.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (ticketList.get(j).getDay() < ticketList.get(j - 1).getDay()) {
				temp = ticketList.get(j);
				ticketList.set(j, ticketList.get(j - 1));
				ticketList.set((j - 1), temp);
				}	
			}
		}
	}
	
	private void sortById() {
		Ticket temp;
		for (int i = 1; i < ticketList.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (ticketList.get(j).getId() < ticketList.get(j - 1).getId()) {
				temp = ticketList.get(j);
				ticketList.set(j, ticketList.get(j - 1));
				ticketList.set((j - 1), temp);
				}	
			}
		}
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