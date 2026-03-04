package system;


import parking.ParkingSlot;
import parking.Ticket;
import vehicles.Vehicle;
import vehicles.VehicleRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingManager {

    private final ParkingLot      lot      = new ParkingLot();
    private final VehicleRegistry registry = new VehicleRegistry();
    private final List<Ticket>    tickets  = new ArrayList<>();

    //Core operations

    //Parks a vehicle: assigns a slot, registers the vehicle, issues a Ticket.
    //Also syncs the vehicle's entryTime field with the current hour.
    public Ticket parkVehicle(Vehicle vehicle) {
        if (registry.isRegistered(vehicle.getPlateNumber())) {
            System.out.println("  Vehicle " + vehicle.getPlateNumber()
                    + " is already in the lot.");
            return null;
        }

        // Sync entryTime to current hour (vehicle already has it from VehicleFactory,
        // but set again here as the authoritative park moment)
        vehicle.setEntryTime(LocalDateTime.now().getHour());

        ParkingSlot slot = lot.assignSlot(vehicle);
        if (slot == null) return null;

        registry.register(vehicle);
        Ticket ticket = new Ticket(slot, vehicle);
        tickets.add(ticket);
        return ticket;
    }


    //Closes the ticket, sets the vehicle's exitTime, removes it from the lot.
    //Call ticket.closeTicket() BEFORE calling this so duration is accurate.

    public boolean removeVehicle(String plateNumber) {
        Ticket ticket = findTicketByPlate(plateNumber);
        if (ticket != null) {
            // Sync exitTime on the Vehicle so vehicle.calculateFee() works correctly
            long hours = ticket.getDurationHours();
            int  exit  = ticket.getVehicle().getEntryTime() + (int) hours;
            ticket.getVehicle().setExitTime(exit);
        }
        registry.unregister(plateNumber);
        return lot.freeSlot(plateNumber);
    }

    //Finds the active (open) ticket for the given plate.

    public Ticket findTicketByPlate(String plateNumber) {
        for (Ticket t : tickets) {
            if (!t.isClosed() &&
                    t.getVehicle().getPlateNumber().equalsIgnoreCase(plateNumber)) {
                return t;
            }
        }
        return null;
    }

    /** Finds the slot currently occupied by the vehicle with the given plate. */
    public ParkingSlot findSlotByPlate(String plateNumber) {
        return lot.findSlotByPlate(plateNumber);
    }

    /** Prints the full slot status table via ParkingLot. */
    public void printAllSlots() {
        lot.printStatus();
    }
}