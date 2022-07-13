import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void clearRentals() {
			System.out.println("Name: " + getName() +
					"\tRentals: " + getRentals().size()) ;
			for ( Rental rental: getRentals() ) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
			}

			List<Rental> rentals = new ArrayList<Rental>() ;
			setRentals(rentals);
	}

	public void returnVideo(String videoTitle) {
		List<Rental> customerRentals = getRentals() ;
		for ( Rental rental: customerRentals ) {
			if ( rental.isVideoTheRented(videoTitle) ) {
				rental.returnVideo();
				rental.getVideo().setRented(false);
				break ;
			}
		}
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";
		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = each.getCharge();
			int eachPoint = each.getPoint();
			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + each.getDayRented() + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";
			totalCharge+=eachCharge;
			totalPoint+=eachPoint;
		}
		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result ;
	}

	public void printCustomer(){
		System.out.println("Name: " + getName() +
				"\tRentals: " + getRentals().size()) ;
	}
}
