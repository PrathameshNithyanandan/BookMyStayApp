public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager m = new AddOnServiceManager();

        String reservationId = "Single-1";

        m.addService(reservationId, new AddOnService("Breakfast", 500));
        m.addService(reservationId, new AddOnService("Spa", 700));
        m.addService(reservationId, new AddOnService("Airport Pickup", 300));

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + m.calculateTotalServiceCost(reservationId));
    }
}
