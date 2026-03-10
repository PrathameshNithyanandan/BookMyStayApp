public class UseCase4RoomSearch {

public static void main(String[] args) {
Room singleRoom = new SingleRoom();
Room doubleRoom = new DoubleRoom();
Room suiteRoom = new SuiteRoom();

RoomInventory inventory = new RoomInventory();
RoomSearchService searchService = new RoomSearchService();

System.out.println("Room Search");
System.out.println();

searchService.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
}
}
