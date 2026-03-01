package vehicles;

public class Motorbike extends Vehicle {
    private int engineCapacity;
    private boolean hasSidecar;
    private String motorbikeModel;

    public Motorbike(String ownerName, String plateNumber, int entryTime,
                     int engineCapacity, boolean hasSidecar, String motorbikeModel) {
        super(ownerName, plateNumber, entryTime);
        this.engineCapacity = engineCapacity;
        this.hasSidecar = hasSidecar;
        this.motorbikeModel = motorbikeModel;
    }

    @Override
    public String toString() {
        return toString() + " | Model: " + motorbikeModel +
                ", Engine: " + engineCapacity + "cc, Sidecar: " + (hasSidecar ? "Yes" : "No");
    }
}

