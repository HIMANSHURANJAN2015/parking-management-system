package model;

import model.constant.ParkingGateType;

public class ParkingGate {
    private long id;
    private String gateName;
    private ParkingGateType parkingGateType;
    private ParkingAttendant parkingAttendant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public ParkingGateType getParkingGateType() {
        return parkingGateType;
    }

    public void setParkingGateType(ParkingGateType parkingGateType) {
        this.parkingGateType = parkingGateType;
    }

    public ParkingAttendant getParkingAttendant() {
        return parkingAttendant;
    }

    public void setParkingAttendant(ParkingAttendant parkingAttendant) {
        this.parkingAttendant = parkingAttendant;
    }
}