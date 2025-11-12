package hotel.repository;

import hotel.domain.Invoice;
import hotel.repository.CSVUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceRepository {
    private List<Invoice> invoices = new ArrayList<>();
    private static final String FILE_PATH = "data/invoices.dat";

    // Load từ file
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            invoices = (List<Invoice>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File chưa tồn tại, bỏ qua
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading invoices: " + e.getMessage());
        }
    }

    // Save vào file
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(invoices);
        } catch (IOException e) {
            throw new RuntimeException("Error saving invoices: " + e.getMessage());
        }
    }

    // CRUD
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public void removeInvoice(String id) {
        invoices.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Invoice> findInvoiceById(String id) {
        return invoices.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices);
    }

    // Export ra CSV
    public void exportInvoicesToCSV(String filename) {
        CSVUtils.exportInvoicesToCSV(invoices, filename);
    }

    // Tính doanh thu theo tháng (giả sử từ danh sách invoices)
    public double getRevenueByMonth(int year, int month) {
        return invoices.stream()
                .filter(i -> i.getDate().getYear() == year && i.getDate().getMonthValue() == month)
                .mapToDouble(Invoice::getTotal)
                .sum();
    }
}