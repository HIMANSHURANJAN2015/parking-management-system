package model;

import model.constant.*;

public class Slab{

    private long id;
    private int startHour;
    private int endHour;
    private double price;
    private VehicleType vehicleType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public double getPrice() {
        return price;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
