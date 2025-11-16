package hotel.model;

public class SpaService extends Service {
    public SpaService() {
        super("Spa" , 50.0 , "Trị liệu spa thư giãn");
    }

    @Override
    public double getCost(){
        return getPrice();
    }
}

