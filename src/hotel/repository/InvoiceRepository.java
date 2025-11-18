package hotel.repository;

import hotel.domain.Invoice;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;

public class InvoiceRepository implements Repository<Invoice> {
    private List<Invoice> invoices = new ArrayList<>();  // Đúng field

    @Override
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(invoices);  // Dùng invoices
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            invoices = (List<Invoice>) ois.readObject();  // Dùng invoices
        }
    }

    @Override
    public void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,BookingID,Total,Details,Date\n");
            for (Invoice invoice : invoices) {  // Dùng invoices
                writer.write((invoice.getId() != null ? invoice.getId() : "") + "," +
                        (invoice.getBookingId() != null ? invoice.getBookingId() : "") + "," +
                        invoice.getTotal() + "," +
                        (invoice.getDetails() != null ? invoice.getDetails() : "") + "," +
                        (invoice.getDate() != null ? invoice.getDate() : "") + "\n");
            }
        }
    }

    @Override
    public void importFromCSV(String filename) throws IOException {
        invoices.clear();  // Dùng invoices
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Invoice invoice = new Invoice(parts[1], Double.parseDouble(parts[2]), parts[3]);
                invoice.setId(parts[0]);  // Set ID từ CSV
                invoices.add(invoice);  // Dùng invoices
            }
        }
    }

    @Override
    public List<Invoice> getAll() { return invoices; }  // Dùng invoices

    @Override
    public void add(Invoice item) { invoices.add(item); }  // Dùng invoices
}

