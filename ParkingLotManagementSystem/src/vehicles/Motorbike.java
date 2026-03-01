package vehicles;

public class Motorbike extends Vehicle {
    private int engineCapacity;
    private boolean hasSidecar;
    private String motorbikeModel;

    public Motorbike(String ownerName, String plateNumber, int entryTime,
                     int engineCapacity, boolean hasSidecar, String motorbikeModel) {
        super(ownerName, "Motorbike", plateNumber);
        this.engineCapacity = engineCapacity;
        this.hasSidecar = hasSidecar;
        this.motorbikeModel = motorbikeModel;
    }

    public int     getEngineCapacity() { return engineCapacity; }
    public boolean hasSidecar()        { return hasSidecar;     }
    public String  getMotorbikeModel() { return motorbikeModel; }

    @Override
    public String toString() {
        return toString() + " | Model: " + motorbikeModel +
                ", Engine: " + engineCapacity + "cc, Sidecar: " + (hasSidecar ? "Yes" : "No");
    }
}

