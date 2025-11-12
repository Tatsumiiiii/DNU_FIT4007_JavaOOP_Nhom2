package hotel.repository;

import hotel.domain.Booking;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {
    private List<Booking> bookings = new ArrayList<>();
    private static final String FILE_PATH = "data/bookings.dat";


    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            bookings = (List<Booking>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading bookings: " + e.getMessage());
        }
    }


    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            throw new RuntimeException("Error saving bookings: " + e.getMessage());
        }
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(String id) {
        bookings.removeIf(b -> b.getId().equals(id));
    }

    public Optional<Booking> findBookingById(String id) {
        return bookings.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
    
    public List<Booking> findBookingsByCustomerId(String customerId) {
        return bookings.stream().filter(b -> b.getCustomerId().equals(customerId)).toList();
    }
}