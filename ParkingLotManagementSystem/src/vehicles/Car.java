package vehicles;

public class Car extends Vehicle {
    private String carBrand;
    private String carModel;
    private String transmissionType;

    public Car(String ownerName, String plateNumber, int entryTime, int exitTime,
               String carBrand, String carModel, String transmissionType) {
        super(ownerName, "Car", plateNumber, entryTime, exitTime);
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.transmissionType = transmissionType;
    }

    @Override
    public double calculateFee() {
        int duration = Math.max(1, getExitTime() - getEntryTime());
        return duration * 50.0; // Cars pay ₱50 per hour
    }

    @Override
    public String toString() {
        return super.toString() + " | Brand: " + carBrand + ", Model: " + carModel +
                ", Transmission: " + transmissionType;
    }
}
