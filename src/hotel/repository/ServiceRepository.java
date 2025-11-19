package hotel.repository;

import hotel.model.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;

public class ServiceRepository implements Repository<Service> {
    private List<Service> services = new ArrayList<>();  // Đúng field

    @Override
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(services);  // Dùng services
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            services = (List<Service>) ois.readObject();  // Dùng services
        }
    }

    @Override
    public void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,Name,Price,Description\n");
            for (Service service : services) {  // Dùng services
                writer.write((service.getId() != null ? service.getId() : "") + "," +
                        (service.getName() != null ? service.getName() : "") + "," +
                        service.getPrice() + "," +
                        (service.getDescription() != null ? service.getDescription() : "") + "\n");
            }
        }
    }

    @Override
    public void importFromCSV(String filename) throws IOException {
        services.clear();  // Dùng services
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Service service;
                switch (parts[1]) {
                    case "Bữa sáng": service = new hotel.model.BreakfastService(); break;
                    case "Spa": service = new hotel.model.SpaService(); break;
                    case "Thuê xe": service = new hotel.model.CarRentalService(); break;
                    case "Giặt là": service = new hotel.model.LaundryService(); break;
                    default: service = new hotel.model.BreakfastService(); break;
                }
                service.setId(parts[0]);  // Set ID từ CSV
                services.add(service);  // Dùng services
            }
        }
    }

    @Override
    public List<Service> getAll() { return services; }  // Dùng services

    @Override
    public void add(Service item) { services.add(item); }  // Dùng services
}

