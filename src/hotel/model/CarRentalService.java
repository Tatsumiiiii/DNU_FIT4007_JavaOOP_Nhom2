package hotel.model;

public class CarRentalService extends Service {
    public CarRentalService() {
        super("Dịch vụ cho thuê xe " , 30.0 , "Giá thuê xe theo ngày");
    }

    @Override
    public double getCost(){
        return getPrice();
    }
}

