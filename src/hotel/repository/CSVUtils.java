package hotel.repository;

import hotel.domain.Invoice;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtils {
    // Export danh sách Invoice ra file CSV
    public static void exportInvoicesToCSV(List<Invoice> invoices, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Header
            writer.write("ID,BookingID,Total,Details,Date\n");
            // Data
            for (Invoice invoice : invoices) {
                writer.write(String.format("%s,%s,%.2f,%s,%s\n",
                        invoice.getId(),
                        invoice.getBookingId(),
                        invoice.getTotal(),
                        invoice.getDetails().replace(",", ";"), // Escape comma
                        invoice.getDate().toString()
                ));
            }
            System.out.println("Invoices exported to " + filename);
        } catch (IOException e) {
            throw new RuntimeException("Error exporting to CSV: " + e.getMessage());
        }
    }
}