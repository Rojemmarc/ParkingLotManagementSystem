package vehicles;

public class Truck extends Vehicle {

    private double cargoCapacity; // in tonnes
    private int    axleCount;
    private String truckModel;

    public Truck(String ownerName, String plateNumber,
                 double cargoCapacity, int axleCount, String truckModel) {
        super(ownerName, "Truck", plateNumber);
        this.cargoCapacity = cargoCapacity;
        this.axleCount     = axleCount;
        this.truckModel    = truckModel;
    }

    public double getCargoCapacity() { return cargoCapacity; }
    public int    getAxleCount()     { return axleCount;     }
    public String getTruckModel()    { return truckModel;    }

    @Override
    public String toString() {
        return "Truck [" + getPlateNumber() + "] owned by " + getOwnerName()
                + " | Model: "          + truckModel
                + ", Cargo: "           + cargoCapacity + "T"
                + ", Axles: "           + axleCount;
    }
}