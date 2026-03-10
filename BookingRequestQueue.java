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

public void displayBookingRequests() {
for(Reservation r:bookingQueue) {
r.displayReservation();
System.out.println();
}
}
}

public class UseCase5BookingRequestQueue {
public static void main(String[] args) {
BookingRequestQueue requestQueue=new BookingRequestQueue();

Reservation r1=new Reservation("Arun","Single");
Reservation r2=new Reservation("Meena","Double");
Reservation r3=new Reservation("Rahul","Suite");

requestQueue.addBookingRequest(r1);
requestQueue.addBookingRequest(r2);
requestQueue.addBookingRequest(r3);

System.out.println("Booking Request Queue");
System.out.println();
requestQueue.displayBookingRequests();
}
}

