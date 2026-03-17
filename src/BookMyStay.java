
public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        BookingHistory h = new BookingHistory();

        h.addReservation(new Reservation("R101", "Arun", "Single"));
        h.addReservation(new Reservation("R102", "Bala", "Double"));
        h.addReservation(new Reservation("R103", "Charan", "Suite"));

        BookingReportService s = new BookingReportService();
        s.showReport(h.getAllReservations());
    }
}
