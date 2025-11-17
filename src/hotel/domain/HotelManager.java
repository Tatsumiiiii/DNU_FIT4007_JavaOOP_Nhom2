package hotel.domain;

import hotel.exceptionHandling.*;
import hotel.model.*;
import hotel.repository.*;
import java.io.*;

import java.util.*;

public class HotelManager implements Persistable {
    private RoomRepository roomRepo = new RoomRepository();
    private CustomerRepository customerRepo = new CustomerRepository();
    private ServiceRepository serviceRepo = new ServiceRepository();
    private BookingRepository bookingRepo = new BookingRepository();
    private InvoiceRepository invoiceRepo = new InvoiceRepository();
    private List<Payment> payments = new ArrayList<>();

    public List<Room> getRooms() { return roomRepo.getAll(); }
    public List<Customer> getCustomers() {
        return customerRepo.getAll();
    }
    public List<Booking> getBookings() {
        return bookingRepo.getAll();
    }
    public List<Service> getServices() {
        return serviceRepo.getAll();
    }
    public List<Invoice> getInvoices() {
        return invoiceRepo.getAll();
    }
    public List<Payment> getPayments() {
        return payments;
    }

    public HotelManager() {
        createSampleCSVIfNotExists();
        try {
            importAllFromCSV();
            System.out.println("Đã tải dữ liệu từ file CSV.");
        } catch (Exception e) {
            System.out.println("Lỗi tải từ CSV, hệ thống sẽ dùng dữ liệu trống.");
        }
    }
}
