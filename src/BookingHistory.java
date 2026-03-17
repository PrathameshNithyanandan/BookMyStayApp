class BookingHistory {
    private List<Reservation> list;
    public BookingHistory() {
        list = new ArrayList<>();
    }
    public void addReservation(Reservation r) {
        list.add(r);
    }
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(list);
    }
}
