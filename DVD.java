import java.util.Date;

public class DVD extends Video{
    public DVD(String title,int priceCode, Date registeredDate) {
        super(title, priceCode, registeredDate);
        penalty = 3 ;
        limit = 2 ;
    }
}
