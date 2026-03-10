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
