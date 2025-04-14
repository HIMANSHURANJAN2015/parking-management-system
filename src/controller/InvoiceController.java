package controller;

import model.Invoice;
import service.*;
import util.InputUtils;
import util.ParkingLotUtils;

import java.util.Scanner;

public class InvoiceController {
    private InvoiceService invoiceService;
    private ParkingSpotService parkingSpotService;
    private Scanner sc;

    public InvoiceController(InvoiceService invoiceService, ParkingSpotService parkingSpotService) {
        this.invoiceService = invoiceService;
        this.parkingSpotService = parkingSpotService;
        this.sc = new Scanner(System.in);
    }

    public void createInvoice(){
        try {
            //Taking ticketId and gateId from user
            long ticketId = InputUtils.getValidLong(sc, "Enter the ticket id");
            long gateId = InputUtils.getValidLong(sc, "Enter the gate id");
            Invoice invoice = this.invoiceService.createInvoice(ticketId, gateId);
            ParkingLotUtils.print(invoice);
            //TODO Customer pays
            //After customer pays, checkout the vehicle
            this.parkingSpotService.vehicleCheckout(invoice.getParkingTicket().getParkingSpot());
        } catch(Exception e){
            System.out.println("OOPS!!! Failed to generate invoice. Error: "+e.getMessage());
        }
    }
}
