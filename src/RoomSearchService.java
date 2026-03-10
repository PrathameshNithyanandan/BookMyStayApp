import java.util.Map;

public class RoomSearchService {

public void searchAvailableRooms(
RoomInventory inventory,
Room singleRoom,
Room doubleRoom,
Room suiteRoom) {

Map<String, Integer> availability = inventory.getRoomAvailability();

if (availability.get("Single") > 0) {
System.out.println("Single Room:");
singleRoom.displayRoomDetails();
System.out.println("Available: " + availability.get("Single"));
System.out.println();
}

if (availability.get("Double") > 0) {
System.out.println("Double Room:");
doubleRoom.displayRoomDetails();
System.out.println("Available: " + availability.get("Double"));
System.out.println();
}

if (availability.get("Suite") > 0) {
System.out.println("Suite Room:");
suiteRoom.displayRoomDetails();
System.out.println("Available: " + availability.get("Suite"));
}
}
}
