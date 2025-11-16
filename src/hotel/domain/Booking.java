package hotel.domain;

import hotel.exceptionHandling.InvalidCancellationException;
import hotel.model.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Serializable, Billable {
    private static final long serialVersionUID = 1L;
    private static int bookingCounter = 1;
    public String id;
    private String customerId;
    private String roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private List<Service> services;
    private double totalCost;
    private boolean isCancelled;

    public Booking(String customerId, String roomId, LocalDate checkIn, LocalDate checkOut) {
        this.id = "B" +  bookingCounter++;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.services = new ArrayList<>();
        this.totalCost = 0.0;
        this.isCancelled = false;
    }

    // Logic đặt phòng: Gọi từ Room, tính tổng chi phí
    public void confirmBooking(Room room) throws Exception {
        room.bookRoom(checkIn, checkOut);
        this.totalCost = calculateTotal(checkIn, checkOut);
    }

    // Logic hủy phòng: Chính sách hủy, hoàn tiền một phần
    public double cancelBooking(Room room, LocalDate cancelDate) throws InvalidCancellationException {
        if (isCancelled) {
            throw new InvalidCancellationException("Đặt phòng đã bị hủy.");
        }
        long daysBefore = java.time.temporal.ChronoUnit.DAYS.between(cancelDate, checkIn);
        double refund = 0.0;
        if (daysBefore >= 1) {
            refund = totalCost * 0.5; // Hoàn 50%
        }
        room.cancelBooking();
        this.isCancelled = true;
        return refund;
    }

    // Thêm dịch vụ và cập nhật tổng chi phí
    public void addService(Service service) {
        services.add(service);
        totalCost += service.calculateTotal(checkIn, checkOut);
    }

    //Billable: Tính tổng (phòng + dịch vụ)
    @Override
    public double calculateTotal(LocalDate checkIn, LocalDate checkOut) {
        return totalCost;
    }

    // Getters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getRoomId() {
        return roomId;
    }
    public LocalDate getCheckIn() {
        return checkIn;
    }
    public LocalDate getCheckOut() {
        return checkOut;
    }
    public List<Service> getServices() {
        return services;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public boolean isCancelled() {
        return isCancelled;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
