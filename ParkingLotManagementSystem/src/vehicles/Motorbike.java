package vehicles;

public class Motorbike extends Vehicle {
    private int engineCapacity;
    private boolean hasSidecar;
    private String motorbikeModel;

    public Motorbike(String ownerName, String plateNumber, int entryTime, int exitTime,
                     int engineCapacity, boolean hasSidecar, String motorbikeModel) {
        super(ownerName, "Motorbike", plateNumber, entryTime, exitTime);
        this.engineCapacity = engineCapacity;
        this.hasSidecar = hasSidecar;
        this.motorbikeModel = motorbikeModel;
    }

    @Override
    public double calculateFee() {
        int duration = getExitTime() - getEntryTime();
        return duration * 30.0; // Motorbikes pay ₱30 per hour
    }

    @Override
    public String toString() {
        return super.toString() + " | Model: " + motorbikeModel +
                ", Engine: " + engineCapacity + "cc, Sidecar: " + (hasSidecar ? "Yes" : "No");
    }
}
