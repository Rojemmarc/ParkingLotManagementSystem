package vehicles;

import java.util.ArrayList;
import java.util.List;

// FIX: Was completely empty. Implements vehicle record as described in the
//      layout report: "Maintains a record of all vehicles currently parked."
public class VehicleRegistry {

    private final List<Vehicle> registry = new ArrayList<>();

    /** Registers a vehicle when it enters the lot. */
    public void register(Vehicle vehicle) {
        registry.add(vehicle);
    }

    /** Removes a vehicle from the registry when it exits. Returns true if found. */
    public boolean unregister(String plateNumber) {
        return registry.removeIf(
                v -> v.getPlateNumber().equalsIgnoreCase(plateNumber)
        );
    }

    /** Finds a parked vehicle by plate. Returns null if not found. */
    public Vehicle findByPlate(String plateNumber) {
        for (Vehicle v : registry) {
            if (v.getPlateNumber().equalsIgnoreCase(plateNumber)) return v;
        }
        return null;
    }

    /** Returns true if the vehicle is currently registered. */
    public boolean isRegistered(String plateNumber) {
        return findByPlate(plateNumber) != null;
    }

    /** Returns all currently registered vehicles. */
    public List<Vehicle> getAllVehicles() {
        return List.copyOf(registry);
    }

    public int getCount() { return registry.size(); }
}