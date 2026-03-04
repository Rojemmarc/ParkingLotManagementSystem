package vehicles;

public abstract class Vehicle {
    private String ownerName;
    private String vehicleType;
    private String plateNumber;
    private int entryTime;
    private int exitTime;

    public Vehicle(String ownerName, String vehicleType, String plateNumber, int entryTime, int exitTime) {
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.plateNumber = plateNumber;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    // Getters
    public String getOwnerName() { return ownerName; }
    public String getVehicleType() { return vehicleType; }
    public String getPlateNumber() { return plateNumber; }
    public int getEntryTime() { return entryTime; }
    public int getExitTime() { return exitTime; }
    public String getOwner() { return ownerName; }

    // Setters
    public void setEntryTime(int entryTime) { this.entryTime = entryTime; }
    public void setExitTime(int exitTime) { this.exitTime = exitTime; }

    // Abstract method for fee calculation
    public abstract double calculateFee();

    @Override
    public String toString() {
        return vehicleType + " [" + plateNumber + "] owned by " + ownerName +
                " | Entry: " + entryTime + " | Exit: " + exitTime;
    }
}
