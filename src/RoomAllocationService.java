import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomAllocationService {
private Set<String> allocatedRoomIds;
private Map<String,Set<String>> allocatedRoomsByType;

public RoomAllocationService() {
allocatedRoomIds=new HashSet<>();
allocatedRoomsByType=new HashMap<>();
allocatedRoomsByType.put("Single",new HashSet<>());
allocatedRoomsByType.put("Double",new HashSet<>());
allocatedRoomsByType.put("Suite",new HashSet<>());
}

public void processBookingRequest(BookingRequestQueue requestQueue,RoomInventory inventory) {
while(requestQueue.hasRequests()) {
Reservation reservation=requestQueue.getNextRequest();
String roomType=reservation.getRoomType();

if(inventory.getAvailability(roomType)>0) {
String roomId=generateRoomId(roomType,allocatedRoomsByType.get(roomType).size()+1);

if(!allocatedRoomIds.contains(roomId)) {
allocatedRoomIds.add(roomId);
allocatedRoomsByType.get(roomType).add(roomId);
inventory.updateAvailability(roomType,inventory.getAvailability(roomType)-1);

System.out.println("Reservation Confirmed");
System.out.println("Guest Name: "+reservation.getGuestName());
System.out.println("Room Type: "+roomType);
System.out.println("Assigned Room ID: "+roomId);
System.out.println();
}
else {
System.out.println("Duplicate room allocation blocked");
System.out.println();
}
}
else {
System.out.println("Reservation Failed");
System.out.println("Guest Name: "+reservation.getGuestName());
System.out.println("Room Type: "+roomType);
System.out.println("No rooms available");
System.out.println();
}
}
}

private String generateRoomId(String roomType,int number) {
if(roomType.equals("Single")) {
return "S"+number;
}
if(roomType.equals("Double")) {
return "D"+number;
}
return "SU"+number;
}
}
