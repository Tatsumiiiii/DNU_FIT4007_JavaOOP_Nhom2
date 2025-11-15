package hotel.model;

public class LaundryService extends Service{
    public LaundryService(){
        super("Giặt là" , 15.0 , "Dịch vụ giặt là");
    }

    @Override
    public double getCost(){
        return getPrice();
    }
}

