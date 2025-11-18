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