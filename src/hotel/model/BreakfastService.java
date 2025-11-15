package hotel.model;

public class BreakfastService extends Service {
    public BreakfastService() {
        super("Breakfast", 20.0, "Dịch vụ bữa sáng hàng ngày");
    }

    @Override
    public double getCost() {
        return getPrice(); // 20$/lần
    }
}
