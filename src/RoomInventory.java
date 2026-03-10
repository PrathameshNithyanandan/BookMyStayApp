import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
private Map<String,Integer> roomAvailability;
private Map<String,Integer> beds;
private Map<String,Integer> size;
private Map<String,Double> price;

public RoomInventory() {
roomAvailability=new HashMap<>();
beds=new HashMap<>();
size=new HashMap<>();
price=new HashMap<>();
initializeInventory();
}

private void initializeInventory() {
beds.put("Single Room",1);
beds.put("Double Room",2);
beds.put("Suite Room",3);

size.put("Single Room",250);
size.put("Double Room",400);
size.put("Suite Room",750);

price.put("Single Room",1500.0);
price.put("Double Room",2500.0);
price.put("Suite Room",5000.0);

roomAvailability.put("Single Room",5);
roomAvailability.put("Double Room",3);
roomAvailability.put("Suite Room",2);
}

public void displayRoomDetails(String roomType) {
System.out.println("Beds: "+beds.get(roomType));
System.out.println("Size: "+size.get(roomType)+" sqft");
System.out.println("Price per night: "+price.get(roomType));
}

public int getAvailability(String roomType) {
return roomAvailability.get(roomType);
}

public void updateAvailability(String roomType,int count) {
roomAvailability.put(roomType,count);
}
}

