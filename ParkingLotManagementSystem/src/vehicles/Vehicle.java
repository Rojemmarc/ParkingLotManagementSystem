package vehicles;

public class Vehicle {
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

    public String getOwnerName() { return ownerName; }
    public String getVehicleType() { return vehicleType; }
    public String getPlateNumber() { return plateNumber; }
    public int getEntryTime() { return entryTime; }
    public int getExitTime() { return exitTime; }

    public abstract String toString();

}
