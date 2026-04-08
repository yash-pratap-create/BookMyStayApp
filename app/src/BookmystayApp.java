/**
 * Book My Stay - Use Case 5: Booking Request (First-Come-First-Served)
 *
 * This single file contains all classes for Use Case 5.
 * Demonstrates fair handling of booking requests using Queue (FIFO).
 */

import java.util.LinkedList;
import java.util.Queue;

// ==================== RESERVATION CLASS ====================
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// ==================== BOOKING REQUEST QUEUE CLASS ====================
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

// ==================== MAIN CLASS ====================
public class BookmystayApp{

    public static void main(String[] args) {

        // Display application header
        System.out.println("Booking Request Queue");
        System.out.println("====================================");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to the queue (in arrival order)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process queued booking requests in FIFO order
        System.out.println("\nProcessing booking requests in FIFO order:\n");

        while (bookingQueue.hasPendingRequests()) {
            Reservation next = bookingQueue.getNextRequest();
            System.out.println("Processing booking for Guest: " + next.getGuestName()
                    + ", Room Type: " + next.getRoomType());
        }

        System.out.println("\nAll booking requests processed successfully!");
    }
}