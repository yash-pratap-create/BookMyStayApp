import java.util.*;

public class BookmystayApp{

    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 2);
            inventory.put("Double", 2);
            inventory.put("Suite", 1);
        }

        public boolean isValidRoomType(String type) {
            return inventory.containsKey(type);
        }
    }

    static class ReservationValidator {

        public void validate(String guestName, String roomType, RoomInventory inventory)
                throws InvalidBookingException {

            if (guestName == null || guestName.trim().isEmpty()) {
                throw new InvalidBookingException("Guest name cannot be empty.");
            }

            if (roomType == null || roomType.trim().isEmpty()) {
                throw new InvalidBookingException("Room type cannot be empty.");
            }

            if (!inventory.isValidRoomType(capitalize(roomType))) {
                throw new InvalidBookingException("Invalid room type selected.");
            }
        }

        private String capitalize(String str) {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

    static class BookingRequestQueue {
        private Queue<String> queue = new LinkedList<>();

        public void addRequest(String request) {
            queue.add(request);
        }
    }

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            validator.validate(guestName, roomType, inventory);

            bookingQueue.addRequest(guestName + " - " + roomType);

            System.out.println("Booking request added successfully.");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}