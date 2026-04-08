import java.util.*;

public class BookmystayApp {

    static class Service {
        private String serviceName;
        private double cost;

        public Service(String serviceName, double cost) {
            this.serviceName = serviceName;
            this.cost = cost;
        }

        public double getCost() {
            return cost;
        }
    }

    static class AddOnServiceManager {
        private Map<String, List<Service>> servicesByReservation = new HashMap<>();

        public void addService(String reservationId, Service service) {
            servicesByReservation
                    .computeIfAbsent(reservationId, k -> new ArrayList<>())
                    .add(service);
        }

        public double calculateTotalServiceCost(String reservationId) {
            double total = 0;
            List<Service> services = servicesByReservation.getOrDefault(reservationId, new ArrayList<>());
            for (Service s : services) {
                total += s.getCost();
            }
            return total;
        }
    }

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Spa", 1000));

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}