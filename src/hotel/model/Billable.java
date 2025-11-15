package hotel.model;

import java.time.LocalDate;
public interface Billable {
    double calculateTotal(LocalDate checkIn, LocalDate checkOut);
}
