package hotel.model;

public class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Phòng hạng sang", 300.0, 6);
    }

    @Override
    public double getPrice() {
        return getBasePrice() * 1.5;
    }
}
