import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) {
			this.status = 1;
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented = getDayRented();

		if ( daysRented <= 2) return limit ;

		limit = video.getLimit();
		return limit ;
	}


	public boolean isVideoTheRented(String videoTitle){
		return video.getTitle().equals(videoTitle) && video.isRented();
	}


	public int getDayRented(){
		if (getStatus() == 1) { // returned Video
			long diff = getReturnDate().getTime() - getRentDate().getTime();
			return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - getRentDate().getTime();
			return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		}

	}
	public double  getCharge(){

		double eachCharge = 0;
		int daysRented = getDayRented();

		switch (getVideo().getPriceCode()) {
			case Video.REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
		}


		return eachCharge;
	}


	public int getPoint(){
		{
			int eachPoint = 0 ;
			int daysRented = getDayRented();


			eachPoint++;

			if ((getVideo().getPriceCode() == Video.NEW_RELEASE) )
				eachPoint++;

			if ( daysRented > getDaysRentedLimit() )
				eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty()) ;

			return eachPoint;
		}
	}

	public void printRental(){
		System.out.print("\tTitle: " + getVideo().getTitle() + " ") ;
		System.out.print("\tPrice Code: " + getVideo().getPriceCode()) ;
	}
}
