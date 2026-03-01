package parking;
//  Author  : Quezon, Lanhce

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import vehicles.*;

public class Ticket {

    //Shared formatter
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");

    //Auto-incrementing ID counter
    private static int nextId = 1;

    //Private fields (Encapsulation)
    private final int           ticketId;
    private final ParkingSlot   slot;
    private final Vehicle       vehicle;
    private final LocalDateTime entryTime;
    private       LocalDateTime exitTime;   // set when the vehicle leaves
    private       boolean       closed;     // true once the ticket is settled

    //Constructor
    //Issues a new ticket at the moment of parking.
    public Ticket(ParkingSlot slot, Vehicle vehicle) {
        this.ticketId  = nextId++;
        this.slot      = slot;
        this.vehicle   = vehicle;
        this.entryTime = LocalDateTime.now();
        this.exitTime  = null;
        this.closed    = false;
    }

        //Core behaviour
     //Closes the ticket by recording the exit time.
     //Can only be called once per ticket.
    public void closeTicket() {
        if (closed) {
            System.out.println("Ticket #" + ticketId + " is already closed.");
            return;
        }
        this.exitTime = LocalDateTime.now();
        this.closed   = true;
    }

    //Computes how many hours the vehicle was parked.
    //Always rounds up to the nearest whole hour (e.g., 1 h 10 min → 2 h).
    public long getDurationHours() {
        LocalDateTime end = (exitTime != null) ? exitTime : LocalDateTime.now();
        long minutes = Duration.between(entryTime, end).toMinutes();
        return Math.max(1, (long) Math.ceil(minutes / 60.0));
    }

    //Getters
    public int           getTicketId()  { return ticketId;  }
    public ParkingSlot   getSlot()      { return slot;      }
    public Vehicle       getVehicle()   { return vehicle;   }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime()  { return exitTime;  }
    public boolean       isClosed()     { return closed;    }

    //Display helper
    //Prints a formatted ticket to the console.
    public void printTicket() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.printf ("║   PARKING TICKET  #%-17d║%n", ticketId);
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf ("║  Slot     : %-3d (%-9s)          ║%n",
                slot.getSlotId(), slot.getSlotType());
        System.out.printf ("║  Plate    : %-25s║%n", vehicle.getPlateNumber());
        System.out.printf ("║  Owner    : %-25s║%n", vehicle.getOwner());
        System.out.printf ("║  Type     : %-25s║%n",
                vehicle.getClass().getSimpleName());
        System.out.printf ("║  Entry    : %-25s║%n",
                entryTime.format(FORMATTER));
        if (closed) {
            System.out.printf("║  Exit     : %-25s║%n",
                    exitTime.format(FORMATTER));
            System.out.printf("║  Duration : %-2d hour(s)%17s║%n",
                    getDurationHours(), "");
        } else {
            System.out.println("║  Exit     : (still parked)           ║");
        }
        System.out.println("╚══════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        return String.format("Ticket #%d | Plate: %s | Slot: %d | Entry: %s | %s",
                ticketId,
                vehicle.getPlateNumber(),
                slot.getSlotId(),
                entryTime.format(FORMATTER),
                closed ? "CLOSED" : "OPEN");
    }
}