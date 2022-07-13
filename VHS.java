import java.util.Date;

public class VHS extends Video{
    public VHS(String title,int priceCode, Date registeredDate) {
        super(title, priceCode, registeredDate);
        penalty = 1 ;
        limit = 5;
    }
}
