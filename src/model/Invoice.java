package model;

import java.util.Date;

public class Invoice {
    private long id;
    private Date exitTime;
    private ParkingTicket parkingTicket;
    private ParkingGate exitGate;
    private ParkingAttendant attendant;
    private double amount;
    private Payment payment;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public ParkingGate getExitGate() {
        return exitGate;
    }

    public void setExitGate(ParkingGate exitGate) {
        this.exitGate = exitGate;
    }

    public ParkingAttendant getAttendant() {
        return attendant;
    }

    public void setAttendant(ParkingAttendant attendant) {
        this.attendant = attendant;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}