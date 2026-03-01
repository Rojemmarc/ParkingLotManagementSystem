package parking;

//  Author  : Quezon, Lanhce

public class FeeCalculator {

    // ── Rate table (PHP) ──────────────────────────────────────
    //  Each vehicle type has: base fee (first hour) + hourly rate
    //  (every succeeding hour).  All stored as constants so they
    //  can be updated in one place.

    // Car
    private static final double CAR_BASE_FEE     = 50.00;
    private static final double CAR_HOURLY_RATE  = 30.00;

    // Motorbike
    private static final double BIKE_BASE_FEE    = 30.00;
    private static final double BIKE_HOURLY_RATE = 20.00;

    // Truck
    private static final double TRUCK_BASE_FEE   = 80.00;
    private static final double TRUCK_HOURLY_RATE = 50.00;

    // Constructor
    // Utility-style class; only one instance is ever needed.
    public FeeCalculator() { }

    //Core method (Polymorphism dispatch)

    //Calculates the total parking fee for a vehicle.
    public double calculateFee(Vehicle vehicle, long durationHours) {
        if (durationHours < 1) durationHours = 1;

        if (vehicle instanceof Car) {
            return computeFee(CAR_BASE_FEE, CAR_HOURLY_RATE, durationHours);
        } else if (vehicle instanceof Motorbike) {
            return computeFee(BIKE_BASE_FEE, BIKE_HOURLY_RATE, durationHours);
        } else if (vehicle instanceof Truck) {
            return computeFee(TRUCK_BASE_FEE, TRUCK_HOURLY_RATE, durationHours);
        } else {
            // Fallback for any future Vehicle subtype
            System.out.println("[FeeCalculator] Unknown vehicle type. Applying Car rates.");
            return computeFee(CAR_BASE_FEE, CAR_HOURLY_RATE, durationHours);
        }
    }

    //
    Overload: convenience method that reads duration directly from a Ticket.
    public double calculateFee(Ticket ticket) {
        return calculateFee(ticket.getVehicle(), ticket.getDurationHours());
    }

    //Private helper

    //General fee formula:
    //first hour  → baseFee
    //remaining hours → (durationHours - 1) × hourlyRate
    private double computeFee(double baseFee, double hourlyRate, long hours) {
        if (hours == 1) return baseFee;
        return baseFee + (hours - 1) * hourlyRate;
    }

    //Display / receipt helper

    //Prints a detailed fee breakdown to the console.
    public void printFeeBreakdown(Vehicle vehicle, long durationHours) {
        double fee = calculateFee(vehicle, durationHours);
        String type = vehicle.getClass().getSimpleName();

        double base, rate;
        if (vehicle instanceof Car) {
            base = CAR_BASE_FEE;  rate = CAR_HOURLY_RATE;
        } else if (vehicle instanceof Motorbike) {
            base = BIKE_BASE_FEE; rate = BIKE_HOURLY_RATE;
        } else {
            base = TRUCK_BASE_FEE; rate = TRUCK_HOURLY_RATE;
        }

        System.out.println("┌─────────────────────────────────────┐");
        System.out.printf ("│   FEE BREAKDOWN  [%s]%n", type);
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf ("│  Base fee  (1st hour)  : PHP %7.2f│%n", base);
        if (durationHours > 1) {
            System.out.printf("│  Extra hrs (%2d × PHP%5.2f): PHP %7.2f│%n",
                    durationHours - 1, rate, (durationHours - 1) * rate);
        }
        System.out.println("├─────────────────────────────────────┤");
        System.out.printf ("│  TOTAL                 : PHP %7.2f│%n", fee);
        System.out.println("└─────────────────────────────────────┘");
    }

    //Static rate info (for menu display)

    //Prints the current rate table.
    public static void printRateTable() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║              PARKING RATES (PHP)              ║");
        System.out.println("╠══════════╦════════════════╦═══════════════════╣");
        System.out.println("║  Type    ║  First Hour    ║  Succeeding Hours ║");
        System.out.println("╠══════════╬════════════════╬═══════════════════╣");
        System.out.printf ("║  Car     ║  PHP %6.2f    ║  PHP %6.2f / hr   ║%n",
                CAR_BASE_FEE, CAR_HOURLY_RATE);
        System.out.printf ("║  Motorbike║ PHP %6.2f    ║  PHP %6.2f / hr   ║%n",
                BIKE_BASE_FEE, BIKE_HOURLY_RATE);
        System.out.printf ("║  Truck   ║  PHP %6.2f    ║  PHP %6.2f / hr   ║%n",
                TRUCK_BASE_FEE, TRUCK_HOURLY_RATE);
        System.out.println("╚══════════╩════════════════╩═══════════════════╝");
    }
}