package hotel.repository;

import hotel.model.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();
    private static final String FILE_PATH = "data/customers.dat";

    // Load từ file
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            customers = (List<Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File chưa tồn tại, bỏ qua
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading customers: " + e.getMessage());
        }
    }

    // Save vào file
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            throw new RuntimeException("Error saving customers: " + e.getMessage());
        }
    }

    // CRUD
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(String id) {
        customers.removeIf(c -> c.getId().equals(id));
    }

    public Optional<Customer> findCustomerById(String id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Optional<Customer> findCustomerByName(String name) {
        return customers.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<Customer> findCustomerByPhone(String phone) {
        return customers.stream().filter(c -> c.getPhone().equals(phone)).findFirst();
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}