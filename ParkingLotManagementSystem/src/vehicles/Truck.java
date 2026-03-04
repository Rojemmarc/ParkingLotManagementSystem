package vehicles;

public class Truck extends Vehicle {

    private double cargoCapacity; // in tonnes
    private int    axleCount;
    private String truckModel;

    public Truck(String ownerName, String plateNumber, int entryTime, int exitTime,
                 double cargoCapacity, int axleCount, String truckModel) {
        super(ownerName, "Truck", plateNumber, entryTime, exitTime);
        this.cargoCapacity = cargoCapacity;
        this.axleCount     = axleCount;
        this.truckModel    = truckModel;
    }

    public double getCargoCapacity() { return cargoCapacity; }
    public int    getAxleCount()     { return axleCount;     }
    public String getTruckModel()    { return truckModel;    }


    @Override
    public double calculateFee() {
        int duration = Math.max(1, getExitTime() - getEntryTime());
        return duration * 80.0; // Trucks: PHP 80 / hour
    }

    @Override
    public String toString() {
        return "Truck [" + getPlateNumber() + "] owned by " + getOwnerName()
                + " | Model: "          + truckModel
                + ", Cargo: "           + cargoCapacity + "T"
                + ", Axles: "           + axleCount;
    }
}