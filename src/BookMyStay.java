import java.util.*;

class Booking {
    private String reservationId;
    private String roomType;
    private String roomId;
    private boolean cancelled;

    public Booking(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.cancelled = false;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        cancelled = true;
    }

    public String toString() {
        return "Reservation ID: " + reservationId + ", Room Type: " + roomType + ", Room ID: " + roomId + ", Cancelled: " + cancelled;
    }
}

class RoomInventory {
    private Map<String, Integer> count;
    private Map<String, Stack<String>> freeRooms;

    public RoomInventory() {
        count = new HashMap<>();
        freeRooms = new HashMap<>();

        addRoomType("Single", new String[]{"S1", "S2"});
        addRoomType("Double", new String[]{"D1", "D2"});
        addRoomType("Suite", new String[]{"SU1"});
    }

    private void addRoomType(String type, String[] ids) {
        Stack<String> st = new Stack<>();
        for (int i = ids.length - 1; i >= 0; i--) {
            st.push(ids[i]);
        }
        freeRooms.put(type, st);
        count.put(type, ids.length);
    }

    public String allocateRoom(String roomType) {
        if (!count.containsKey(roomType) || count.get(roomType) <= 0) {
            return null;
        }
        count.put(roomType, count.get(roomType) - 1);
        return freeRooms.get(roomType).pop();
    }

    public void releaseRoom(String roomType, String roomId) {
        freeRooms.get(roomType).push(roomId);
        count.put(roomType, count.get(roomType) + 1);
    }

    public void showInventory() {
        System.out.println("current inventory:");
        for (String s : count.keySet()) {
            System.out.println(s + " -> " + count.get(s));
        }
    }
}

class BookingManager {
    private Map<String, Booking> bookings;
    private Stack<String> rollbackStack;
    private RoomInventory inventory;

    public BookingManager(RoomInventory inventory) {
        this.inventory = inventory;
        bookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    public void confirmBooking(String reservationId, String roomType) {
        String roomId = inventory.allocateRoom(roomType);

        if (roomId == null) {
            System.out.println("booking failed for " + reservationId);
            return;
        }

        Booking b = new Booking(reservationId, roomType, roomId);
        bookings.put(reservationId, b);
        System.out.println("booking confirmed: " + reservationId + " room " + roomId);
    }

    public void cancelBooking(String reservationId) {
        if (!bookings.containsKey(reservationId)) {
            System.out.println("cancellation failed: reservation not found");
            return;
        }

        Booking b = bookings.get(reservationId);

        if (b.isCancelled()) {
            System.out.println("cancellation failed: already cancelled");
            return;
        }

        rollbackStack.push(b.getRoomId());
        inventory.releaseRoom(b.getRoomType(), b.getRoomId());
        b.cancel();

        System.out.println("booking cancelled: " + reservationId);
        System.out.println("rollback room released: " + rollbackStack.peek());
    }

    public void showBookings() {
        System.out.println("booking history:");
        for (Booking b : bookings.values()) {
            System.out.println(b);
        }
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        RoomInventory r = new RoomInventory();
        BookingManager m = new BookingManager(r);

        m.confirmBooking("R101", "Single");
        m.confirmBooking("R102", "Double");

        m.cancelBooking("R101");
        m.cancelBooking("R101");
        m.cancelBooking("R999");

        r.showInventory();
        m.showBookings();
    }
}
