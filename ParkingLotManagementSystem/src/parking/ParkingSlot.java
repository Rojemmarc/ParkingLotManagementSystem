package parking;import vehicles.*;
//  ParkingSlot.java

import vehicles.*;

public class ParkingSlot {

    //Slot types that mirror the vehicle hierarchy
    public enum SlotType {
        CAR, MOTORBIKE, TRUCK
    }

    //Private fields (Encapsulation)
    private final int     slotId;       // immutable once assigned
    private final SlotType slotType;    // what kind of vehicle fits here
    private boolean       isOccupied;   // true when a vehicle is parked
    private Vehicle       parkedVehicle; // null when the slot is free

    //Constructor
    // Creates a new, empty parking slot.

    public ParkingSlot(int slotId, SlotType slotType) {
        this.slotId        = slotId;
        this.slotType      = slotType;
        this.isOccupied    = false;
        this.parkedVehicle = null;
    }

    //Core behaviours
    //Parks a vehicle in this slot.
    public boolean parkVehicle(Vehicle vehicle) {
        if (isOccupied) {
            System.out.println("[Slot " + slotId + "] Already occupied.");
            return false;
        }
        if (!isCompatible(vehicle)) {
            System.out.println("[Slot " + slotId + "] Incompatible vehicle type.");
            return false;
        }
        this.parkedVehicle = vehicle;
        this.isOccupied    = true;
        System.out.println("[Slot " + slotId + "] Vehicle " +
                vehicle.getPlateNumber() + " parked successfully.");
        return true;
    }

    // Removes the currently parked vehicle and frees the slot.
    public Vehicle removeVehicle() {
        if (!isOccupied) {
            System.out.println("[Slot " + slotId + "] No vehicle to remove.");
            return null;
        }
        Vehicle removed    = this.parkedVehicle;
        this.parkedVehicle = null;
        this.isOccupied    = false;
        System.out.println("[Slot " + slotId + "] Vehicle " +
                removed.getPlateNumber() + " removed.");
        return removed;
    }


     //Checks whether this slot can accept the given vehicle.
     //Polymorphic: uses instanceof to dispatch to the correct subtype.

    private boolean isCompatible(Vehicle vehicle) {
        return switch (slotType) {
            case CAR       -> vehicle instanceof Car;
            case MOTORBIKE -> vehicle instanceof Motorbike;
            case TRUCK     -> vehicle instanceof Truck;
        };
    }

    //Getters (no public setters for slotId / slotType)

    public int       getSlotId()        { return slotId;        }
    public SlotType  getSlotType()      { return slotType;      }
    public boolean   isOccupied()       { return isOccupied;    }
    public Vehicle   getParkedVehicle() { return parkedVehicle; }

    //Display helper
    @Override
    public String toString() {
        String status = isOccupied
                ? "OCCUPIED by " + parkedVehicle.getPlateNumber()
                : "AVAILABLE";
        return String.format("Slot %-3d [%-9s] — %s", slotId, slotType, status);
    }
}