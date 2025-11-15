package hotel.model;

public class VipRoom extends Room {
    public VipRoom() {
        super("Phòng VIP", 200.0, 4);
    }

    @Override
    public double getPrice() {
        return getBasePrice() * 1.2; // Thêm 20% phí VIP
    }
}

