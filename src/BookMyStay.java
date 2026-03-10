public class UseCase6RoomAllocationService {
public static void main(String[] args) {
BookingRequestQueue requestQueue=new BookingRequestQueue();

requestQueue.addBookingRequest(new Reservation("Arun","Single"));
requestQueue.addBookingRequest(new Reservation("Meena","Double"));
requestQueue.addBookingRequest(new Reservation("Rahul","Suite"));

RoomInventory inventory=new RoomInventory();
RoomAllocationService allocationService=new RoomAllocationService();

allocationService.processBookingRequest(requestQueue,inventory);
}
}
