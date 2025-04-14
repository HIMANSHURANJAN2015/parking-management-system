package controller;

import model.*;
import model.constant.VehicleType;
import service.*;
import util.InputUtils;
import util.ParkingLotUtils;
import java.util.*;

import java.util.Scanner;

public class ParkingTicketController {
    private ParkingTicketService ticketService;
    Scanner sc;

    public ParkingTicketController(ParkingTicketService ticketService) {
        this.ticketService = ticketService;
        sc = new Scanner(System.in);
    }

    public void generateTicket(){
        try {
            //Ask user to enter long gateId, stroing vehicle number, string vehicletype, List<String> Additional services
            long gateId = InputUtils.getValidLong(sc, "Enter Gate ID");
            String vehicleNumber = InputUtils.getValidString(sc, "Enter Vehicle Number");
            VehicleType vehicleType = InputUtils.getValidVehicleType(sc, "Enter Vehicle Type");
            int numberOfAdditionalServices = InputUtils.getValidInt(sc, "Enter Number of Additional Services. 0 for no additional service");
            List<String> additionalServices = new ArrayList<>();
            for(int i=1; i<=numberOfAdditionalServices; i++){
                String service = InputUtils.getValidString(sc, "Enter Additional Service " + i);
                additionalServices.add(service);
            }
            //Calling parking ticket service
            ParkingTicket ticket = this.ticketService.generateTicket(gateId, vehicleNumber, vehicleType.toString(), additionalServices);
            ParkingLotUtils.print(ticket);
        } catch(Exception e) {
            System.out.println("OOPS!!! Failed to generate parking ticket. Error: "+e.getMessage());
        }
    }
}
