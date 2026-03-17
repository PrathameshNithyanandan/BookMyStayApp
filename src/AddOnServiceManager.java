import java.util.*;
class AddOnServiceManager {
    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        double total = 0;

        List<AddOnService> list = servicesByReservation.get(reservationId);
        if (list != null) {
            for (AddOnService s : list) {
                total += s.getCost();
            }
        }

        return total;
    }
}
