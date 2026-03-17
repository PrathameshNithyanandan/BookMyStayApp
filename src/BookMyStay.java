import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> q;

    public BookingRequestQueue() {
        q = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        q.offer(r);
    }

    public Reservation getNextRequest() {
        return q.poll();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }
}

class RoomInventory {
    private Map<String, Integer> count;
    private Map<String, Queue<String>> rooms;

    public RoomInventory() {
        count = new HashMap<>();
        rooms = new HashMap<>();

        addRoomType("Single", new String[]{"Single-1", "Single-2", "Single-3", "Single-4", "Single-5"});
        addRoomType("Double", new String[]{"Double-1", "Double-2", "Double-3"});
        addRoomType("Suite", new String[]{"Suite-1", "Suite-2"});
    }

    private void addRoomType(String type, String[] ids) {
        Queue<String> q = new LinkedList<>();
        for (String id : ids) {
            q.offer(id);
        }
        rooms.put(type, q);
        count.put(type, ids.length);
    }

    public String allocate(String roomType) {
        if (!count.containsKey(roomType) || count.get(roomType) <= 0) {
            return null;
        }

        count.put(roomType, count.get(roomType) - 1);
        return rooms.get(roomType).poll();
    }

    public void showInventory() {
        System.out.println();
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + count.get("Single"));
        System.out.println("Double: " + count.get("Double"));
        System.out.println("Suite: " + count.get("Suite"));
    }
}

class RoomAllocationService {
    public void allocateRoom(Reservation r, RoomInventory inventory) {
        String roomId = inventory.allocate(r.getRoomType());

        if (roomId != null) {
            r.setRoomId(roomId);
            System.out.println("Booking confirmed for Guest: " + r.getGuestName() + ", Room ID: " + roomId);
        } else {
            System.out.println("Booking failed for Guest: " + r.getGuestName() + ", no " + r.getRoomType() + " rooms available");
        }
    }
}

class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
        BookingRequestQueue bookingQueue,
        RoomInventory inventory,
        RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (true) {
            Reservation reservation;

            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) {
                    break;
                }
                reservation = bookingQueue.getNextRequest();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanamthi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        Thread t1 = new Thread(
            new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        Thread t2 = new Thread(
            new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        inventory.showInventory();
    }
}
