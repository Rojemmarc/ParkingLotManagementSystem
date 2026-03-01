package vehicles;

public class Car extends Vehicle {
    private String carBrand;
    private String carModel;
    private String transmissionType;


    public Car(String ownerName, String plateNumber, int entryTime, int exitTime,
               int numberOfDoors, String fuelType, String transmissionType, String carModel) {
        super(ownerName, "Car", plateNumber);
        this.carBrand = carModel;
        this.carModel = carModel;
        this.transmissionType = transmissionType;
    }

    public String getCarBrand() { return carBrand; }
    public String getCarModel() { return carModel; }
    public String getTransmissionType() { return transmissionType; }

    @Override
    public String toString() {
        return "Car [" + getPlateNumber() + "] owned by " + getOwnerName() + " | Model: " + carModel + ", Doors: "
                + ", Transmission: " + transmissionType;
    }
}
