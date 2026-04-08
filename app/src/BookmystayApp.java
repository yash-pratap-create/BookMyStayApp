import java.util.Map;

public class BookmystayApp {

    /**
     * Displays available rooms along with their details and pricing.
     * This method performs read-only access to inventory and room data.
     */
    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        // Read-only access to availability
        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("=== Available Rooms ===");

        // Single Room
        if (availability.getOrDefault("Single", 0) > 0) {
            displayRoomDetails("Single", availability.get("Single"), singleRoom);
        }

        // Double Room
        if (availability.getOrDefault("Double", 0) > 0) {
            displayRoomDetails("Double", availability.get("Double"), doubleRoom);
        }

        // Suite Room
        if (availability.getOrDefault("Suite", 0) > 0) {
            displayRoomDetails("Suite", availability.get("Suite"), suiteRoom);
        }
    }

    // Helper method to avoid duplication (Separation of Concerns)
    private void displayRoomDetails(String type, int count, Room room) {
        System.out.println("\nRoom Type: " + type);
        System.out.println("Available: " + count);
        System.out.println("Price: $" + room.getPrice());
        System.out.println("Amenities: " + room.getAmenities());
    }
}