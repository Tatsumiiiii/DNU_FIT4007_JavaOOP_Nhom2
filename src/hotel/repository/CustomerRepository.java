package hotel.repository;

import hotel.model.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository<Customer> {
    private List<Customer> customers = new ArrayList<>();  // Đúng field

    @Override
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(customers);  // Dùng customers
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            customers = (List<Customer>) ois.readObject();  // Dùng customers
        }
    }

    @Override
    public void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,Name,Phone,Email,Address\n");
            for (Customer customer : customers) {  // Dùng customers
                writer.write((customer.getId() != null ? customer.getId() : "") + "," +
                        (customer.getName() != null ? customer.getName() : "") + "," +
                        (customer.getPhone() != null ? customer.getPhone() : "") + "," +
                        (customer.getEmail() != null ? customer.getEmail() : "") + "," +
                        (customer.getAddress() != null ? customer.getAddress() : "") + "\n");
            }
        }
    }

    @Override
    public void importFromCSV(String filename) throws IOException {
        customers.clear();  // Dùng customers
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Customer customer = new Customer(parts[1], parts[2], parts[3], parts[4]);
                customer.setId(parts[0]);  // Set ID từ CSV
                customers.add(customer);  // Dùng customers
            }
        }
    }

    @Override
    public List<Customer> getAll() { return customers; }  // Dùng customers

    @Override
    public void add(Customer item) { customers.add(item); }  // Dùng customers
}