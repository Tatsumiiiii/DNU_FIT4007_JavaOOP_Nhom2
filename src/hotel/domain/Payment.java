package hotel.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int paymentCounter = 1;
    private String id;
    private String invoiceId;
    private double amount;
    private LocalDate date;

    public Payment(String invoiceId, double amount) {
        this.id = "P" + paymentCounter++;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String getId() {
        return id;
    }
    public String getInvoiceId() {
        return invoiceId;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDate getDate() {
        return date;
    }
}

