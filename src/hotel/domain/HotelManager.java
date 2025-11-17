package hotel.domain;

import hotel.exceptionHandling.*;
import hotel.model.*;
import hotel.repository.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    private void createSampleCSVIfNotExists() {

        new File("src/hotel/data").mkdirs();
        try {
            // Tạo rooms.csv (20 phòng: 10 Thường, 5 VIP, 5 Suite)
            if (!new File("src/hotel/data/rooms.csv").exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/hotel/data/rooms.csv"))) {
                    writer.write("ID,Type,BasePrice,Capacity,Status\n");
                    int idCounter = 1;
                    for (int i = 1; i <= 10; i++) {
                        writer.write(
                                "R" + idCounter + "," + "Thường," + "100.0," + "2," + "CÓ_SẴN\n"
                        );
                        idCounter++;
                    }
                    for (int i = 1; i <= 5; i++) {
                        writer.write("R" + idCounter + "," + "VIP," + "200.0," + "4," + "CÓ_SẴN\n"
                        );
                        idCounter++;
                    }
                    for (int i = 1; i <= 5; i++) {
                        writer.write("R" + idCounter + "," + "Suite," + "300.0," + "6," + "CÓ_SẴN\n"
                        );
                        idCounter++;
                    }
                }
            }


            // Tạo customers.csv (15 khách hàng)
            if (!new File("src/hotel/data/customers.csv").exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/hotel/data/customers.csv"))) {
                    writer.write("ID,Name,Phone,Email,Address\n");
                    writer.write("P1,Nguyễn Văn A,0123456789,nguyenvana@email.com,Số 1 Đường ABC Hà Nội\n");
                    writer.write("P2,Trần Thị B,0987654321,tranthib@email.com,Số 2 Đường DEF TP.HCM\n");
                    writer.write("P3,Lê Văn C,0111111111,levanc@email.com,Số 3 Đường GHI Đà Nẵng\n");
                    writer.write("P4,Phạm Thị D,0222222222,phamthid@email.com,Số 4 Đường JKL Cần Thơ\n");
                    writer.write("P5,Hoàng Văn E,0333333333,hoangvane@email.com,Số 5 Đường MNO Hải Phòng\n");
                    writer.write("P6,Đỗ Thị F,0444444444,dothif@email.com,Số 6 Đường PQR Nha Trang\n");
                    writer.write("P7,Bùi Văn G,0555555555,buivang@email.com,Số 7 Đường STU Vũng Tàu\n");
                    writer.write("P8,Vũ Thị H,0666666666,vuthih@email.com,Số 8 Đường VWX Quy Nhơn\n");
                    writer.write("P9,Đinh Văn I,0777777777,dinhvani@email.com,Số 9 Đường YZ Phan Thiết\n");
                    writer.write("P10,Ngô Thị K,0888888888,ngothik@email.com,Số 10 Đường 123 Buôn Ma Thuột\n");
                    writer.write("P11,Mai Văn L,0999999999,maivanl@email.com,Số 11 Đường 456 Pleiku\n");
                    writer.write("P12,Trương Thị M,0000000000,truongthim@email.com,Số 12 Đường 789 Kon Tum\n");
                    writer.write("P13,Lý Văn N,0123456780,lyvann@email.com,Số 13 Đường ABC Gia Lai\n");
                    writer.write("P14,Tô Thị O,0987654320,tothio@email.com,Số 14 Đường DEF Đắk Lắk\n");
                    writer.write("P15,Đặng Văn P,0111111110,dangvanp@email.com,Số 15 Đường GHI Đắk Nông\n");
                }
            }

            // Tạo services.csv (10 dịch vụ: 3 Bữa sáng, 2 Spa, 3 Thuê xe, 2 Giặt là)
            if (!new File("src/hotel/data/services.csv").exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/hotel/data/services.csv"))) {
                    writer.write("ID,Name,Price,Description\n");
                    writer.write("S1,Bữa sáng,20.0,Dịch vụ bữa sáng hàng ngày\n");
                    writer.write("S2,Bữa sáng,30.0,Dịch vụ bữa sáng hàng ngày\n");
                    writer.write("S3,Bữa sáng,50.0,Dịch vụ bữa sáng hàng ngày\n");
                    writer.write("S4,Spa,60.0,Phiên spa thư giãn\n");
                    writer.write("S5,Spa,100.0,Phiên spa thư giãn\n");
                    writer.write("S6,Thuê xe,35.0,Dịch vụ thuê xe theo ngày\n");
                    writer.write("S7,Thuê xe,40.0,Dịch vụ thuê xe theo ngày\n");
                    writer.write("S8,Thuê xe,50.0,Dịch vụ thuê xe theo ngày\n");
                    writer.write("S9,Giặt là,20.0,Dịch vụ giặt là\n");
                    writer.write("S10,Giặt là,15.0,Dịch vụ giặt là\n");
                }
            }

            // Tạo bookings.csv (20 booking)
            if (!new File("src/hotel/data/bookings.csv").exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/hotel/data/bookings.csv"))) {
                    writer.write("ID,CustomerID,RoomID,CheckIn,CheckOut,TotalCost,IsCancelled\n");
                    for (int i = 1; i <= 20; i++) {
                        String bookingId = "B" + i;
                        String customerId = "C" + i;
                        String roomId = "R" + i;
                        writer.write(bookingId + "," + customerId +", " +roomId + " , " + "2025-10-11," + "2025-11-13," + "0.0," + "false\n");
                    }
                }
            }

            // Tạo payments.csv
            if (!new File("src/hotel/data/payments.csv").exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/hotel/data/payments.csv"))) {
                    writer.write("ID,InvoiceID,Amount,Date\n");
                }
            }

            // Tạo invoices.csv
            if (!new File("src/hotel/data/invoices.csv").exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/hotel/data/invoices.csv"))) {
                    writer.write("ID,BookingID,Total,Details,Date\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Lỗi tạo file CSV mẫu: " + e.getMessage());
        }
    }
}
