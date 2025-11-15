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
}


