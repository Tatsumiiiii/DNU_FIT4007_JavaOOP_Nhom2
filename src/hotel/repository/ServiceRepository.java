package hotel.repository;

import hotel.model.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceRepository {
    private List<Service> services = new ArrayList<>();
    private static final String FILE_PATH = "data/services.dat";

    // Load từ file
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            services = (List<Service>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File chưa tồn tại, bỏ qua
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading services: " + e.getMessage());
        }
    }

    // Save vào file
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(services);
        } catch (IOException e) {
            throw new RuntimeException("Error saving services: " + e.getMessage());
        }
    }

    // CRUD
    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(String id) {
        services.removeIf(s -> s.getId().equals(id));
    }

    public Optional<Service> findServiceById(String id) {
        return services.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public List<Service> getAllServices() {
        return new ArrayList<>(services);
    }
}