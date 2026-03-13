import java.util.HashMap;
import java.util.Map;

/* =========================================================
   CLASS - Room
   Stores room characteristics
   ========================================================= */
class Room {

    String type;
    int beds;
    String size;
    double pricePerNight;
    int availableRooms;

    // Constructor
    public Room(String type, int beds, String size, double pricePerNight, int availableRooms) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
    }

    // Display room details
    public void displayRoom() {
        System.out.println(type + "\t\t" + beds + "\t\t" + size + "\t\t" + pricePerNight + "\t\t" + availableRooms);
    }
}


/* =========================================================
   CLASS - RoomInventory
   Centralized inventory management using HashMap
   ========================================================= */
class RoomInventory {

    private Map<String, Room> roomInventory;

    // Constructor
    public RoomInventory() {
        roomInventory = new HashMap<>();
        initializeInventory();
    }

    // Initialize rooms
    private void initializeInventory() {

        roomInventory.put("Standard",
                new Room("Standard", 1, "200 sq.ft", 2000, 10));

        roomInventory.put("Deluxe",
                new Room("Deluxe", 2, "300 sq.ft", 3500, 6));

        roomInventory.put("Suite",
                new Room("Suite", 3, "450 sq.ft", 6000, 3));
    }

    // Display inventory
    public void displayInventory() {

        System.out.println("Room Type\tBeds\tRoom Size\tPrice/Night\tAvailable Rooms");

        for (Room r : roomInventory.values()) {
            r.displayRoom();
        }
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {

        if (roomInventory.containsKey(roomType)) {
            roomInventory.get(roomType).availableRooms = newCount;
        }
    }
}


/* =========================================================
   MAIN CLASS
   ========================================================= */
public class BookmystayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        System.out.println("Initial Room Inventory:\n");
        inventory.displayInventory();

        System.out.println("\nUpdating Deluxe rooms...\n");

        inventory.updateAvailability("Deluxe", 4);

        System.out.println("Updated Room Inventory:\n");
        inventory.displayInventory();
    }
}

