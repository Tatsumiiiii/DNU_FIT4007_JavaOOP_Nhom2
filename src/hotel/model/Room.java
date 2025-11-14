package hotel.model;


import hotel.exceptionHandling.RoomNotAvailableException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public abstract class Room implements Serializable, Billable {
    private static final long serialVersionUID = 1L;
    private static int roomCounter = 1;
    public String id;
    private String type;
    private double basePrice;
    private int capacity;
    private RoomStatus status;

    public enum RoomStatus {
        AVAILABLE, BOOKED, OCCUPIED, MAINTENANCE
    }

    public Room(String type, double basePrice, int capacity) {
        this.id = "P" + roomCounter++;
        this.type = type;
        this.basePrice = basePrice;
        this.capacity = capacity;
        this.status = RoomStatus.AVAILABLE;
    }

    public abstract double getPrice();

    public void bookRoom(LocalDate checkIn, LocalDate checkOut) throws RoomNotAvailableException {
        if (this.status != RoomStatus.AVAILABLE) {
            throw new RoomNotAvailableException("Room " + id + " Không có sẵn để đặt phòng");
        }
        this.status = RoomStatus.BOOKED;
    }

    // hủy phòng
    public void cancelBooking() {
        if (this.status == RoomStatus.BOOKED) {
            this.status = RoomStatus.AVAILABLE;
        }
    }

    @Override
    public double calculateTotal(LocalDate checkIn, LocalDate checkOut) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        return days * getPrice();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public int getCapacity() {
        return capacity;
    }
    public RoomStatus getStatus() {
        return status;
    }
    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}

