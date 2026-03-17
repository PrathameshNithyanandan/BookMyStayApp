import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return "Reservation ID: " + reservationId + ", Guest: " + guestName + ", Room Type: " + roomType;
    }
}

class HotelData implements Serializable {
    List<Reservation> bookings;
    Map<String, Integer> inventory;

    public HotelData(List<Reservation> bookings, Map<String, Integer> inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

class PersistenceService {
    private String fileName = "hoteldata.ser";

    public void saveData(HotelData data) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(data);
            out.close();
            System.out.println("data saved successfully");
        } catch (Exception e) {
            System.out.println("error while saving data");
        }
    }

    public HotelData loadData() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            HotelData data = (HotelData) in.readObject();
            in.close();
            System.out.println("data restored successfully");
            return data;
        } catch (Exception e) {
            System.out.println("no valid saved data found, starting fresh");
            return new HotelData(new ArrayList<>(), getDefaultInventory());
        }
    }

    private Map<String, Integer> getDefaultInventory() {
        Map<String, Integer> m = new HashMap<>();
        m.put("Single", 5);
        m.put("Double", 3);
        m.put("Suite", 2);
        return m;
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        PersistenceService p = new PersistenceService();

        HotelData data = p.loadData();

        System.out.println("current bookings:");
        for (Reservation r : data.bookings) {
            System.out.println(r);
        }

        System.out.println("current inventory:");
        for (String s : data.inventory.keySet()) {
            System.out.println(s + " -> " + data.inventory.get(s));
        }

        data.bookings.add(new Reservation("R101", "Arun", "Single"));
        data.bookings.add(new Reservation("R102", "Bala", "Double"));

        data.inventory.put("Single", data.inventory.get("Single") - 1);
        data.inventory.put("Double", data.inventory.get("Double") - 1);

        p.saveData(data);

        System.out.println("updated bookings:");
        for (Reservation r : data.bookings) {
            System.out.println(r);
        }

        System.out.println("updated inventory:");
        for (String s : data.inventory.keySet()) {
            System.out.println(s + " -> " + data.inventory.get(s));
        }
    }
}
