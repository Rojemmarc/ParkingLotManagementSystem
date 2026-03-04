package vehicles;

import java.time.LocalDateTime;
import java.util.Scanner;

// FIX: Was completely empty. Main calls VehicleFactory.createVehicle() so this
//      must be fully implemented.
public class VehicleFactory {

    /**
     * Prompts for vehicle-specific details and returns the correct subtype.
     *
     * @param typeChoice  1 = Car, 2 = Motorbike, 3 = Truck
     * @param plate       already-collected plate number
     * @param owner       already-collected owner name
     * @param scanner     shared Scanner from Main
     * @return the constructed Vehicle, or null for an invalid choice
     */
    public static Vehicle createVehicle(int typeChoice, String plate,
                                        String owner, Scanner scanner) {
        // entryTime = current clock hour (0-23); exitTime = 0 (set on exit)
        int entryHour = LocalDateTime.now().getHour();

        return switch (typeChoice) {
            case 1 -> buildCar(owner, plate, entryHour, scanner);
            case 2 -> buildMotorbike(owner, plate, entryHour, scanner);
            case 3 -> buildTruck(owner, plate, entryHour, scanner);
            default -> null;
        };
    }

    // ── Private builders ──────────────────────────────────────

    private static Car buildCar(String owner, String plate,
                                int entryHour, Scanner sc) {
        System.out.print("  Car brand             : ");
        String brand = sc.nextLine().trim();

        System.out.print("  Car model             : ");
        String model = sc.nextLine().trim();

        System.out.print("  Transmission (Manual/Automatic) : ");
        String trans = sc.nextLine().trim();

        return new Car(owner, plate, entryHour, 0, brand, model, trans);
    }

    private static Motorbike buildMotorbike(String owner, String plate,
                                            int entryHour, Scanner sc) {
        System.out.print("  Motorbike model       : ");
        String model = sc.nextLine().trim();

        int cc = readInt(sc, "  Engine capacity (cc) : ");

        System.out.print("  Has sidecar? (yes/no) : ");
        boolean sidecar = sc.nextLine().trim().equalsIgnoreCase("yes");

        return new Motorbike(owner, plate, entryHour, 0, cc, sidecar, model);
    }

    private static Truck buildTruck(String owner, String plate,
                                    int entryHour, Scanner sc) {
        System.out.print("  Truck model           : ");
        String model = sc.nextLine().trim();

        double cargo = readDouble(sc, "  Cargo capacity (T)   : ");
        int    axles = readInt(sc,    "  Number of axles       : ");

        return new Truck(owner, plate, entryHour, 0, cargo, axles, model);
    }

    // ── Input helpers ─────────────────────────────────────────

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try   { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) {
                System.out.println("  Please enter a whole number.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try   { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) {
                System.out.println("  Please enter a number.");
            }
        }
    }
}