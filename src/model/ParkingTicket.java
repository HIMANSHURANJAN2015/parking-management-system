package model;

import java.util.Date;
import java.util.List;

import model.constant.AdditionalService;

public class ParkingTicket {
    private long id;
    private ParkingSpot parkingSpot;
    private Date entryTime;
    private Vehicle vehicle;
    private ParkingGate entryGate;
    private ParkingAttendant parkingAttendant;
    private List<AdditionalService> additionalServices;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingGate getEntryGate() {
        return entryGate;
    }

    public void setEntryGate(ParkingGate entryGate) {
        this.entryGate = entryGate;
    }

    public ParkingAttendant getParkingAttendant() {
        return parkingAttendant;
    }

    public void setParkingAttendant(ParkingAttendant parkingAttendant) {
        this.parkingAttendant = parkingAttendant;
    }

    public List<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }
}