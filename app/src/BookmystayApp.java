import java.util.*;

public class BookmystayApp {

    // ===================== Reservation =====================
    static class Reservation {
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

    // ===================== Room Inventory =====================
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public void addRooms(String type, int count) {
            inventory.put(type, count);
        }

        public boolean isAvailable(String type) {
            return inventory.getOrDefault(type, 0) > 0;
        }

        public void decrement(String type) {
            if (!isAvailable(type)) {
                throw new IllegalStateException("No rooms left for type: " + type);
            }
            inventory.put(type, inventory.get(type) - 1);
        }
    }

    // ===================== Room Allocation Service =====================
    static class RoomAllocationService {

        private Set<String> allocatedRoomIds = new HashSet<>();
        private Map<String, Set<String>> assignedRoomsByType = new HashMap<>();
        private Map<String, Integer> roomTypeCounters = new HashMap<>();

        public void allocateRoom(Reservation reservation, RoomInventory inventory) {
            String roomType = reservation.getRoomType();

            // Step 1: Check availability
            if (!inventory.isAvailable(roomType)) {
                System.out.println("No rooms available for " + reservation.getGuestName());
                return;
            }

            // Step 2: Generate unique room ID
            String roomId = generateRoomId(roomType);

            // Step 3: Ensure uniqueness
            if (allocatedRoomIds.contains(roomId)) {
                throw new IllegalStateException("Duplicate Room ID detected!");
            }

            // Step 4: Store allocation
            allocatedRoomIds.add(roomId);
            assignedRoomsByType
                    .computeIfAbsent(roomType, k -> new HashSet<>())
                    .add(roomId);

            // Step 5: Update inventory
            inventory.decrement(roomType);

            // Step 6: Confirm booking
            System.out.println("Booking confirmed for Guest: "
                    + reservation.getGuestName()
                    + ", Room ID: " + roomId);
        }

        private String generateRoomId(String roomType) {
            int count = roomTypeCounters.getOrDefault(roomType, 0) + 1;
            roomTypeCounters.put(roomType, count);
            return roomType + "-" + count;
        }
    }

    // ===================== MAIN =====================
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.addRooms("Single", 2);
        inventory.addRooms("Suite", 1);

        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));

        RoomAllocationService allocator = new RoomAllocationService();

        System.out.println("Room Allocation Processing:");

        while (!bookingQueue.isEmpty()) {
            Reservation reservation = bookingQueue.poll();
            allocator.allocateRoom(reservation, inventory);
        }
    }
}