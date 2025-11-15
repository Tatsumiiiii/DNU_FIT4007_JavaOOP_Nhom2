package hotel.model;

import java.io.Serializable;
import java.util.UUID;

public abstract class Service implements Serializable ,Billable{
    private static final long serialVersionUID = 1L;
    public String id;
    private String name;
    private double price;
    private String description;

    public Service(String name, double price, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public abstract double getCost();

    @Override
    public double calculateTotal(java.time.LocalDate checkIn, java.time.LocalDate checkOut) {
        return getCost();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
}


