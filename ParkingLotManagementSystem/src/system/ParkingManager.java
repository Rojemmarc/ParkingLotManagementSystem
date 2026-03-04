package system;

import parking.*;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

//Author: Brandon Carl Raden
public class ParkingManager {

    private final ParkingLot parkingLot;
    private final List<Ticket> activeTickets;

    public ParkingManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.activeTickets = new ArrayList<>();
    }

    // PARK VEHICLE
    public Ticket parkVehicle(Vehicle vehicle) {

        for (ParkingSlot slot : parkingLot.getSlots()) {
            if (!slot.isOccupied() && slot.parkVehicle(vehicle)) {

                Ticket ticket = new Ticket(slot, vehicle);
                activeTickets.add(ticket);

                return ticket;
            }
        }

        return null;
    }

    public void removeVehicle(String plateNumber) {

        Ticket ticket = findTicketByPlate(plateNumber);

        if (ticket == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        ticket.closeTicket();
        ticket.getSlot().removeVehicle();
        activeTickets.remove(ticket);
    }

    public Ticket findTicketByPlate(String plate) {
        for (Ticket ticket : activeTickets) {
            if (ticket.getVehicle().getPlateNumber()
                    .equalsIgnoreCase(plate)) {
                return ticket;
            }
        }
        return null;
    }

    public ParkingSlot findSlotByPlate(String plate) {
        Ticket ticket = findTicketByPlate(plate);
        return (ticket != null) ? ticket.getSlot() : null;
    }

    public void printAllSlots() {
        parkingLot.displayStatus();
    }
}