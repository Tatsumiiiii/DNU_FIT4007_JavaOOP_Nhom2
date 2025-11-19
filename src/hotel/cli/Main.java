package hotel.cli;

import hotel.domain.HotelManager;
import hotel.exceptionHandling.*;
import hotel.model.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static HotelManager manager;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        manager = new HotelManager();
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Chọn chức năng: ");
            try {
                switch (choice) {
                    case 1: manageRooms(); break;
                    case 2: manageCustomers(); break;
                    case 3: manageServices(); break;
                    case 4: bookRoom(); break;
                    case 5: cancelBooking(); break;
                    case 6: addServiceToBooking(); break;
                    case 7: calculateTotalCost(); break;
                    case 8: processPaymentAndInvoice(); break;
                    case 9: showStatistics(); break;
                    case 10: exportImportData(); break;
                    case 0: running = false; break;
                    default: System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
        try {
            manager.saveAll();
            System.out.println("Dữ liệu đã được lưu. Thoát chương trình...");
        } catch (IOException e) {
            System.out.println("Lỗi lưu dữ liệu: " + e.getMessage());
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== Hệ Thống Quản Lý Khách Sạn ===");
        System.out.println("1. Quản lý phòng");
        System.out.println("2. Quản lý khách hàng");
        System.out.println("3. Quản lý dịch vụ");
        System.out.println("4. Đặt phòng");
        System.out.println("5. Hủy phòng");
        System.out.println("6. Đăng ký dịch vụ bổ sung");
        System.out.println("7. Tính tổng chi phí");
        System.out.println("8. Thanh toán & hóa đơn");
        System.out.println("9. Thống kê");
        System.out.println("10. Xuất/Nhập dữ liệu");
        System.out.println("0. Thoát");
    }

    private static void manageRooms() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Quản Lý Phòng ---");
            System.out.println("1. Thêm phòng");
            System.out.println("2. Sửa phòng");
            System.out.println("3. Xóa phòng");
            System.out.println("4. Danh sách phòng");
            System.out.println("0. Quay lại");
            int choice = getIntInput("Chọn: ");
            switch (choice) {
                case 1: addRoom(); break;
                case 2: updateRoom(); break;
                case 3: deleteRoom(); break;
                case 4: listRooms(); break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void manageCustomers() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Quản Lý Khách Hàng ---");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Sửa khách hàng");
            System.out.println("3. Xóa khách hàng");
            System.out.println("4. Danh sách khách hàng");
            System.out.println("0. Quay lại");
            int choice = getIntInput("Chọn: ");
            switch (choice) {
                case 1: addCustomer(); break;
                case 2: updateCustomer(); break;
                case 3: deleteCustomer(); break;
                case 4: listCustomers(); break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void manageServices() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Quản Lý Dịch Vụ ---");
            System.out.println("1. Thêm dịch vụ");
            System.out.println("0. Quay lại");
            int choice = getIntInput("Chọn: ");
            switch (choice) {
                case 1: addService(); break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void showStatistics() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Thống Kê ---");
            System.out.println("1. Doanh thu theo tháng");
            System.out.println("2. Top 3 khách hàng");
            System.out.println("3. Phòng còn trống");
            System.out.println("0. Quay lại");
            int choice = getIntInput("Chọn: ");
            switch (choice) {
                case 1: showRevenueByMonth(); break;
                case 2: showTop3Customers(); break;
                case 3: showAvailableRooms(); break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void exportImportData() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Xuất/Nhập Dữ Liệu ---");
            System.out.println("1. Xuất tất cả ra CSV");
            System.out.println("2. Nhập từ CSV");
            System.out.println("0. Quay lại");
            int choice = getIntInput("Chọn: ");
            switch (choice) {
                case 1: exportAllToCSV(); break;
                case 2: importAllFromCSV(); break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addRoom() {
        System.out.println("Loại phòng: 1. Thường, 2. VIP, 3. Suite");
        int type = getIntInput("Chọn loại: ");
        Room room = null;
        switch (type) {
            case 1: room = new hotel.Model.StandardRoom(); break;
            case 2: room = new hotel.Model.VipRoom(); break;
            case 3: room = new hotel.Model.SuiteRoom(); break;
            default: System.out.println("Loại không hợp lệ."); return;
        }
        manager.addRoom(room);
        System.out.println("Thêm phòng thành công. ID: " + room.getId());
    }

    private static void updateRoom() {
        listRooms();
        String roomId = getStringInput("Nhập ID phòng cần sửa: ");
        System.out.println("Trạng thái mới: 1. CÓ_SẴN, 2. ĐÃ_ĐẶT, 3. ĐANG_SỬ_DỤNG, 4. BẢO_TRÌ");
        int statusChoice = getIntInput("Chọn trạng thái: ");
        Room.RoomStatus status = Room.RoomStatus.AVAILABLE;
        switch (statusChoice) {
            case 1: status = Room.RoomStatus.AVAILABLE; break;
            case 2: status = Room.RoomStatus.BOOKED; break;
            case 3: status = Room.RoomStatus.OCCUPIED; break;
            case 4: status = Room.RoomStatus.MAINTENANCE; break;
            default: System.out.println("Trạng thái không hợp lệ."); return;
        }
        Room updatedRoom = new hotel.Model.StandardRoom();
        updatedRoom.setStatus(status);
        try {
            manager.updateRoom(roomId, updatedRoom);
            System.out.println("Sửa phòng thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void deleteRoom() {
        listRooms();
        String roomId = getStringInput("Nhập ID phòng cần xóa: ");
        try {
            manager.deleteRoom(roomId);
            System.out.println("Xóa phòng thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void addCustomer() {
        String name = getStringInput("Nhập tên: ");
        String phone = getStringInput("Nhập số điện thoại: ");
        String email = getStringInput("Nhập email: ");
        String address = getStringInput("Nhập địa chỉ: ");
        Customer customer = new Customer(name, phone, email, address);
        manager.addCustomer(customer);
        System.out.println("Thêm khách hàng thành công. ID: " + customer.getId());
    }

    private static void updateCustomer() {
        listCustomers();
        String customerId = getStringInput("Nhập ID khách hàng cần sửa: ");
        try {
            manager.updateCustomer(customerId, null);
            System.out.println("Sửa khách hàng thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void deleteCustomer() {
        listCustomers();
        String customerId = getStringInput("Nhập ID khách hàng cần xóa: ");
        try {
            manager.deleteCustomer(customerId);
            System.out.println("Xóa khách hàng thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void addService() {
        System.out.println("Loại dịch vụ: 1. Bữa sáng, 2. Spa, 3. Thuê xe, 4. Giặt là");
        int type = getIntInput("Chọn loại: ");
        Service service = null;
        switch (type) {
            case 1: service = new hotel.Model.BreakfastService(); break;
            case 2: service = new hotel.Model.SpaService(); break;
            case 3: service = new hotel.Model.CarRentalService(); break;
            case 4: service = new hotel.Model.LaundryService(); break;
            default: System.out.println("Loại không hợp lệ."); return;
        }
        manager.addService(service);
        System.out.println("Thêm dịch vụ thành công. ID: " + service.getId());
    }

    private static void bookRoom() {
        listCustomers();
        String customerId = getStringInput("Nhập ID khách hàng: ");
        listRooms();
        String roomId = getStringInput("Nhập ID phòng: ");
        LocalDate checkIn = getDateInput("Ngày nhận phòng");
        LocalDate checkOut = getDateInput("Ngày trả phòng");
        try {
            var booking = manager.bookRoom(customerId, roomId, checkIn, checkOut);
            System.out.println("Đặt phòng thành công. ID đặt phòng: " + booking.getId());
        } catch (RoomNotAvailableException e) {
            System.out.println("Đặt phòng thất bại: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void cancelBooking() {
        System.out.println("Danh sách đặt phòng:");
        manager.getBookings().forEach(b -> System.out.println("ID: " + b.getId() + ", Phòng: " + b.getRoomId()));
        String bookingId = getStringInput("Nhập ID đặt phòng cần hủy: ");
        LocalDate cancelDate = getDateInput("Ngày hủy");
        try {
            double refund = manager.cancelBooking(bookingId, cancelDate);
            System.out.println("Hủy phòng thành công. Hoàn tiền: $" + refund);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void addServiceToBooking() {
        System.out.println("Danh sách đặt phòng:");
        manager.getBookings().forEach(b -> System.out.println("ID: " + b.getId()));
        String bookingId = getStringInput("Nhập ID đặt phòng: ");
        var booking = manager.findBookingById(bookingId);
        if (booking == null) {
            System.out.println("Không tìm thấy đặt phòng.");
            return;
        }
        System.out.println("Dịch vụ: 1. Bữa sáng, 2. Spa, 3. Thuê xe, 4. Giặt là");
        int serviceType = getIntInput("Chọn dịch vụ: ");
        Service service = null;
        switch (serviceType) {
            case 1: service = new hotel.Model.BreakfastService(); break;
            case 2: service = new hotel.Model.SpaService(); break;
            case 3: service = new hotel.Model.CarRentalService(); break;
            case 4: service = new hotel.Model.LaundryService(); break;
            default: System.out.println("Dịch vụ không hợp lệ."); return;
        }
        booking.addService(service);
        System.out.println("Đăng ký dịch vụ thành công.");
    }