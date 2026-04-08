import java.util.*;

public class BookmystayApp {

    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        public void increment(String type) {
            inventory.put(type, inventory.getOrDefault(type, 0) + 1);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }
    }

    static class CancellationService {

        private Stack<String> releasedRoomIds;
        private Map<String, String> reservationRoomTypeMap;

        public CancellationService() {
            releasedRoomIds = new Stack<>();
            reservationRoomTypeMap = new HashMap<>();
        }

        public void registerBooking(String reservationId, String roomType) {
            reservationRoomTypeMap.put(reservationId, roomType);
        }

        public void cancelBooking(String reservationId, RoomInventory inventory) {
            if (!reservationRoomTypeMap.containsKey(reservationId)) {
                System.out.println("Invalid cancellation request.");
                return;
            }

            String roomType = reservationRoomTypeMap.get(reservationId);

            releasedRoomIds.push(reservationId);

            inventory.increment(roomType);

            reservationRoomTypeMap.remove(reservationId);

            System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
        }

        public void showRollbackHistory() {
            System.out.println("\nRollback History (Most Recent First):");
            for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
                System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        service.registerBooking("Single-1", "Single");

        service.cancelBooking("Single-1", inventory);

        service.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: " + inventory.getAvailability("Single"));
    }
}