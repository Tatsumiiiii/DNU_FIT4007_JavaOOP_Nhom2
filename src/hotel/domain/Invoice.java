package hotel.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int invoiceCounter = 1;
    public String id;
    private String bookingId;
    private double total;
    private String details;
    private LocalDate date;

    public Invoice(String bookingId, double total, String details) {
        this.id = "I" + invoiceCounter++;
        this.bookingId = bookingId;
        this.total = total;
        this.details = details;
        this.date = LocalDate.now();
    }

    // Getters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBookingId() {
        return bookingId;
    }
    public double getTotal() {
        return total;
    }
    public String getDetails() {
        return details;
    }
    public LocalDate getDate() {
        return date;
    }
}