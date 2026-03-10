import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {
private Queue<Reservation> bookingQueue;

public BookingRequestQueue() {
bookingQueue=new LinkedList<>();
}

public void addBookingRequest(Reservation reservation) {
bookingQueue.add(reservation);
}

public Reservation getNextRequest() {
return bookingQueue.poll();
}

public boolean hasRequests() {
return !bookingQueue.isEmpty();
}
}

