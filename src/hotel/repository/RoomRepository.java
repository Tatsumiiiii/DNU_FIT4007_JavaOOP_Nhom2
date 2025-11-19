package hotel.repository;

import hotel.model.Room;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;

public class RoomRepository implements Repository<Room> {
    private List<Room> rooms = new ArrayList<>();

    @Override
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(rooms);
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            rooms = (List<Room>) ois.readObject();
        }
    }

    @Override
    public void exportToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,Type,BasePrice,Capacity,Status\n");
            for (Room room : rooms) {
                writer.write((room.getId() != null ? room.getId() : "") + "," +
                        (room.getType() != null ? room.getType() : "") + "," +
                        room.getBasePrice() + "," +
                        room.getCapacity() + "," +
                        (room.getStatus() != null ? room.getStatus() : "") + "\n");
            }
        }
    }

    @Override
    public void importFromCSV(String filename) throws IOException {
        rooms.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Room room;
                switch (parts[1]) {
                    case "Thường": room = new hotel.model.StandardRoom(); break;
                    case "VIP": room = new hotel.model.VipRoom(); break;
                    case "Suite": room = new hotel.model.SuiteRoom(); break;
                    default: room = new hotel.model.StandardRoom(); break;
                }

                room.id = parts[0];
                room.setStatus(Room.RoomStatus.valueOf(parts[4].replace("CÓ_SẴN", "AVAILABLE").replace("ĐÃ_ĐẶT", "BOOKED").replace("ĐANG_SỬ_DỤNG", "OCCUPIED").replace("BẢO_TRÌ", "MAINTENANCE")));
                rooms.add(room);
            }
        }
    }

    @Override
    public List<Room> getAll() {
        return rooms;
    }

    @Override
    public void add(Room item) {
        rooms.add(item);
    }
}
