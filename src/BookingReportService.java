public class BookingReportService {
    public void showReport(List<Reservation> list) {
        System.out.println("Booking History Report");
        for (Reservation r : list) {
            System.out.println(r);
        }
        System.out.println("Total Bookings: " + list.size());
    }
}
