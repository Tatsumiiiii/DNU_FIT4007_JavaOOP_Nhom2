package hotel.repository;

import hotel.domain.Booking;
import java.io.*;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository implements Repository<Booking> {
    private List<Booking> bookings = new ArrayList<>();  // Đúng field

    @Override
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(bookings);  // Dùng bookings
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            bookings = (List<Booking>) ois.readObject();  // Dùng bookings
        }
    }

    @Override
    public void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,CustomerID,RoomID,CheckIn,CheckOut,TotalCost,IsCancelled\n");
            for (Booking booking : bookings) {  // Dùng bookings
                writer.write((booking.getId() != null ? booking.getId() : "") + "," +
                        (booking.getCustomerId() != null ? booking.getCustomerId() : "") + "," +
                        (booking.getRoomId() != null ? booking.getRoomId() : "") + "," +
                        (booking.getCheckIn() != null ? booking.getCheckIn() : "") + "," +
                        (booking.getCheckOut() != null ? booking.getCheckOut() : "") + "," +
                        booking.getTotalCost() + "," +
                        booking.isCancelled() + "\n");
            }
        }
    }

    @Override
    public void importFromCSV(String filename) throws IOException {
        bookings.clear();  // Dùng bookings
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Booking booking = new Booking(parts[1], parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                booking.setTotalCost(Double.parseDouble(parts[5]));
                booking.setId(parts[0]);  // Set ID từ CSV
                bookings.add(booking);  // Dùng bookings
            }
        }
    }

    @Override
    public List<Booking> getAll() { return bookings; }  // Dùng bookings

    @Override
    public void add(Booking item) { bookings.add(item); }  // Dùng bookings
}

