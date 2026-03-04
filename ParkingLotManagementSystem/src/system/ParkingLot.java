package system;

import java.util.ArrayList;
import java.util.List;
import parking.ParkingSlot;
import parking.ParkingSlot.SlotType;

//Author: Brandon Carl Raden
public class ParkingLot {

    private final List<ParkingSlot> slots;

    public ParkingLot(int carSlots, int bikeSlots, int truckSlots) {
        slots = new ArrayList<>();

        int id = 1;

        for (int i = 0; i < carSlots; i++)
            slots.add(new ParkingSlot(id++, SlotType.CAR));

        for (int i = 0; i < bikeSlots; i++)
            slots.add(new ParkingSlot(id++, SlotType.MOTORBIKE));

        for (int i = 0; i < truckSlots; i++)
            slots.add(new ParkingSlot(id++, SlotType.TRUCK));
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void displayStatus() {
        for (ParkingSlot slot : slots) {
            System.out.println(slot);
        }
    }
}