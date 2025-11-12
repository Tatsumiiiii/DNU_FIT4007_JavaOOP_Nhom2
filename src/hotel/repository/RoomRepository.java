package hotel.repository;

import hotel.model.Room;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepository {
    private List<Room> rooms = new ArrayList<>();
    private static final String FILE_PATH = "data/rooms.dat";

    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            rooms = (List<Room>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading rooms: " + e.getMessage());
        }
    }


    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(rooms);
        } catch (IOException e) {
            throw new RuntimeException("Error saving rooms: " + e.getMessage());
        }
    }

    // CRUD
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(String id) {
        rooms.removeIf(r -> r.getId().equals(id));
    }

    public Optional<Room> findRoomById(String id) {
        return rooms.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    // Tìm phòng available theo ngày (giả sử không có booking overlap logic phức tạp)
    public List<Room> findAvailableRooms(java.time.LocalDate date) {
        return rooms.stream()
                .filter(r -> r.getStatus() == Room.RoomStatus.AVAILABLE)
                .toList();
    }
}