package hotel.Cli;

import hotel.domain.HotelManager;
import hotel.ExceptionHandling.*;
import hotel.Model.*;
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