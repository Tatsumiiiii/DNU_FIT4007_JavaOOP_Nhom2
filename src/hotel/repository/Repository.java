package hotel.repository;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    void saveToFile(String filename) throws IOException;
    void loadFromFile(String filename) throws IOException, ClassNotFoundException;
    void exportToCSV(String filename) throws IOException;
    void importFromCSV(String filename) throws IOException;
    List<T> getAll();
    void add(T item);
}
