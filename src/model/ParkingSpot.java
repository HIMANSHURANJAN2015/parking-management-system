package model;

import model.constant.ParkingSpotStatus;
import model.constant.ParkingSpotType;
import model.constant.VehicleType;

public class ParkingSpot {
    private long id;
    private int parkingSpotNumber;
    private ParkingSpotStatus parkingSpotStatus;
    private ParkingSpotType parkingSpotType;
    private VehicleType vehicleType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(int parkingSlotNumber) {
        this.parkingSpotNumber = parkingSlotNumber;
    }

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}