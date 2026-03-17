import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String msg) {
        super(msg);
    }
}

class RoomInventory {
    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 2);
        rooms.put("Double", 1);
        rooms.put("Suite", 1);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {
        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("invalid room type: " + roomType);
        }

        int count = rooms.get(roomType);

        if (count <= 0) {
            throw new InvalidBookingException("no rooms available for: " + roomType);
        }

        rooms.put(roomType, count - 1);
        System.out.println("booking confirmed for " + roomType);
    }

    public void showInventory() {
        System.out.println("current inventory:");
        for (String s : rooms.keySet()) {
            System.out.println(s + " -> " + rooms.get(s));
        }
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        RoomInventory r = new RoomInventory();

        try {
            r.bookRoom("Single");
        } catch (InvalidBookingException e) {
            System.out.println("error: " + e.getMessage());
        }

        try {
            r.bookRoom("Deluxe");
        } catch (InvalidBookingException e) {
            System.out.println("error: " + e.getMessage());
        }

        try {
            r.bookRoom("Double");
            r.bookRoom("Double");
        } catch (InvalidBookingException e) {
            System.out.println("error: " + e.getMessage());
        }

        r.showInventory();
    }
}
