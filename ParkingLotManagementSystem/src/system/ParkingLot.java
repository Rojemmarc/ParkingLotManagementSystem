package system;

import parking.ParkingSlot;
import parking.ParkingSlot.SlotType;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final List<ParkingSlot> slots = new ArrayList<>();

    /** Builds the default lot: 5 Car, 3 Motorbike, 2 Truck slots. */
    public ParkingLot() {
        for (int i = 1;  i <= 5;  i++) slots.add(new ParkingSlot(i,  SlotType.CAR));
        for (int i = 6;  i <= 8;  i++) slots.add(new ParkingSlot(i,  SlotType.MOTORBIKE));
        for (int i = 9;  i <= 10; i++) slots.add(new ParkingSlot(i,  SlotType.TRUCK));
    }

    /**
     * Finds the first available slot that accepts the given vehicle type.
     * Parks the vehicle and returns the slot, or null if full.
     */
    public ParkingSlot assignSlot(Vehicle vehicle) {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && slot.parkVehicle(vehicle)) {
                return slot;
            }
        }
        return null;
    }

    /** Frees the slot occupied by the vehicle with the given plate. */
    public boolean freeSlot(String plateNumber) {
        ParkingSlot slot = findSlotByPlate(plateNumber);
        if (slot == null) return false;
        slot.removeVehicle();
        return true;
    }

    /** Finds the slot occupied by the vehicle with the given plate. */
    public ParkingSlot findSlotByPlate(String plateNumber) {
        for (ParkingSlot slot : slots) {
            if (slot.isOccupied() &&
                    slot.getParkedVehicle().getPlateNumber()
                            .equalsIgnoreCase(plateNumber)) {
                return slot;
            }
        }
        return null;
    }

    /** Prints every slot's status to the console. */
    public void printStatus() {
        long occupied  = slots.stream().filter(ParkingSlot::isOccupied).count();
        long available = slots.size() - occupied;
        System.out.println("  Total: " + slots.size()
                + "  |  Occupied: " + occupied
                + "  |  Available: " + available);
        System.out.println("  -----------------------------------------------");
        for (ParkingSlot slot : slots) {
            System.out.println("  " + slot);
        }
    }

    public List<ParkingSlot> getAllSlots() { return List.copyOf(slots); }
}