package hotel.model;

public class StandardRoom extends Room {
    public StandardRoom() {
        super("Phòng tiêu chuẩn", 100.0, 2);
    }

    @Override
    public double getPrice() {
        return getBasePrice();
    }
}

