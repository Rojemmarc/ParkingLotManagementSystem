package system;


import java.util.Scanner;
import parking.FeeCalculator;
import parking.ParkingSlot;
import parking.Ticket;
import vehicles.Vehicle;
import vehicles.VehicleFactory;
import people.Customer;
import people.Staff;
import people.Receipt;

public class Main {

    // Shared resources
    private static final Scanner        scanner  = new Scanner(System.in);
    private static final ParkingManager manager  = new ParkingManager();
    private static final FeeCalculator  feeCalc  = new FeeCalculator();

    // Default on-duty staff member (used when issuing receipts)
    private static final Staff ON_DUTY_STAFF =
            new Staff("S-001", "Maria Santos", Staff.Role.CASHIER, "Day");

    // ── Entry point ───────────────────────────────────────────
    public static void main(String[] args) {
        printBanner();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1  -> parkVehicle();
                case 2  -> removeVehicle();
                case 3  -> viewAllSlots();
                case 4  -> viewTicket();
                case 5  -> FeeCalculator.printRateTable();
                case 6  -> searchVehicle();
                case 0  -> { running = false; goodbye(); }
                default -> System.out.println("  Invalid option. Try again.\n");
            }
        }

        scanner.close();
    }

    // ── OPTION 1 — Park a vehicle ─────────────────────────────
    private static void parkVehicle() {
        System.out.println("\n-- PARK A VEHICLE --------------------------");

        System.out.println("  Vehicle types:");
        System.out.println("    1. Car");
        System.out.println("    2. Motorbike");
        System.out.println("    3. Truck");
        int typeChoice = readInt("  Select type: ");

        String plate = readString("  Plate number : ");
        String owner = readString("  Owner name   : ");

        // VehicleFactory prompts for type-specific details
        Vehicle vehicle = VehicleFactory.createVehicle(typeChoice, plate, owner, scanner);
        if (vehicle == null) {
            System.out.println("  Invalid vehicle type selection.\n");
            return;
        }

        // ParkingManager finds a compatible slot and issues a Ticket
        Ticket ticket = manager.parkVehicle(vehicle);
        if (ticket != null) {
            System.out.println("\n  Parking successful! Keep your ticket.");
            ticket.printTicket();
        } else {
            System.out.println("  No available slot for this vehicle type.\n");
        }
    }

    // ── OPTION 2 — Remove a vehicle and collect fee ───────────
    private static void removeVehicle() {
        System.out.println("\n-- REMOVE A VEHICLE ------------------------");
        String plate = readString("  Enter plate number: ");

        Ticket ticket = manager.findTicketByPlate(plate);
        if (ticket == null) {
            System.out.println("  No active ticket found for plate: " + plate + "\n");
            return;
        }

        // Close ticket — records exit LocalDateTime
        ticket.closeTicket();

        // Calculate fee using Ticket duration (accurate to the minute)
        double fee = feeCalc.calculateFee(ticket);
        System.out.println();
        ticket.printTicket();
        feeCalc.printFeeBreakdown(ticket.getVehicle(), ticket.getDurationHours());
        System.out.printf("%n  Amount due: PHP %.2f%n", fee);

        // Collect customer details and issue receipt via Staff
        System.out.println("\n-- CUSTOMER DETAILS ------------------------");
        String custId      = readString("  Customer ID     : ");
        String custName    = readString("  Customer name   : ");
        String custContact = readString("  Contact number  : ");
        String custEmail   = readString("  Email address   : ");

        Customer customer = new Customer(custId, custName, custContact, custEmail);
        Receipt  receipt  = ON_DUTY_STAFF.issueReceipt(customer, ticket, fee);
        System.out.println();
        receipt.printReceipt();

        // Remove vehicle from lot (also syncs vehicle exitTime)
        manager.removeVehicle(plate);
        System.out.println("\n  Vehicle removed. Slot is now free.\n");
    }

    // ── OPTION 3 — View all parking slots ─────────────────────
    private static void viewAllSlots() {
        System.out.println("\n-- PARKING LOT STATUS ----------------------");
        manager.printAllSlots();
        System.out.println();
    }

    // ── OPTION 4 — View a specific ticket ─────────────────────
    private static void viewTicket() {
        System.out.println("\n-- VIEW TICKET -----------------------------");
        String plate  = readString("  Enter plate number: ");
        Ticket ticket = manager.findTicketByPlate(plate);
        if (ticket == null) {
            System.out.println("  No active ticket found for plate: " + plate + "\n");
        } else {
            ticket.printTicket();
            System.out.printf(
                    "  Current fee (if exiting now): PHP %.2f%n%n",
                    feeCalc.calculateFee(ticket)
            );
        }
    }

    // ── OPTION 6 — Search for a vehicle by plate ──────────────
    private static void searchVehicle() {
        System.out.println("\n-- SEARCH VEHICLE --------------------------");
        String      plate = readString("  Enter plate number: ");
        ParkingSlot slot  = manager.findSlotByPlate(plate);
        if (slot == null) {
            System.out.println("  Vehicle not found in the lot.\n");
        } else {
            System.out.println("  Found: " + slot);
            System.out.println();
        }
    }

    // ── UI helpers ─────────────────────────────────────────────
    private static void printBanner() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║      PARKING LOT MANAGEMENT SYSTEM           ║");
        System.out.println("║              OOP 2 - NepoBabies              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("==============================================");
        System.out.println("  MAIN MENU");
        System.out.println("==============================================");
        System.out.println("  [1] Park a vehicle");
        System.out.println("  [2] Remove vehicle & collect fee");
        System.out.println("  [3] View all parking slots");
        System.out.println("  [4] View ticket by plate");
        System.out.println("  [5] View parking rates");
        System.out.println("  [6] Search vehicle by plate");
        System.out.println("  [0] Exit");
        System.out.println("==============================================");
    }

    private static void goodbye() {
        System.out.println("\n  Thank you for using the Parking Lot System!");
        System.out.println("  Goodbye!\n");
    }

    /** Reads a trimmed, non-empty string. */
    private static String readString(String prompt) {
        String value;
        do {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
        } while (value.isEmpty());
        return value;
    }

    /** Reads a validated integer. */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try   { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }
}