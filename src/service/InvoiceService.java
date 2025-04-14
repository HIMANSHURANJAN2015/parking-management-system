package service;

import model.*;
import model.constant.AdditionalService;
import model.constant.ParkingGateType;
import repository.*;
import exception.*;
import service.strategy.pricing.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class InvoiceService {
    private InvoiceRepository invoiceRepo;
    private ParkingTicketRepository ticketRepo;
    private ParkingGateRepository gateRepo;
    private PricingStrategyFactory pricingFactory;

    public InvoiceService(InvoiceRepository inv, ParkingTicketRepository t, ParkingGateRepository g, PricingStrategyFactory f) {
        this.invoiceRepo = inv;
        this.ticketRepo = t;
        this.gateRepo = g;
        this.pricingFactory = f;
    }

    public Invoice createInvoice(long ticketId, long gateId) throws ParkingTicketNotFoundException, InvalidGateException{
        //Verify and get Ticket Object
        Optional<ParkingTicket> ticketOpt = this.ticketRepo.getTicketById(ticketId);
        if(ticketOpt.isEmpty()) {
            throw new ParkingTicketNotFoundException("Ticket with id: "+ticketId+" not found");
        }
        ParkingTicket ticket = ticketOpt.get();

        //veridfy exit gate
        Optional<ParkingGate> gateOpt = this.gateRepo.findById(gateId);
        if(gateOpt.isEmpty()) {
            throw new InvalidGateException("Gate not found");
        }
        ParkingGate gate = gateOpt.get();
        if(gate.getParkingGateType() != ParkingGateType.EXIT) {
            throw new InvalidGateException("Invoice can be generated only at exit gate");
        }

        //calculation price: Parking fee and Additional service fee.
        Date exitTime = new Date();
        PricingStrategy pricingStrategy = this.pricingFactory.getPricingStrategy(exitTime);
        Double parkingFee = pricingStrategy.calculateAmount(ticket.getEntryTime(), exitTime, ticket.getVehicle().getVehicleType());
        Double additionalServicesFee = 0.0;
        if(ticket.getAdditionalServices() != null) {
            additionalServicesFee = this.calculateAdditionalServiceAmount(ticket.getAdditionalServices());
        }
        Double amount = parkingFee + additionalServicesFee;

        //saving invoice
        Invoice invoice = new Invoice();
        invoice.setParkingTicket(ticket);
        invoice.setExitGate(gate);
        invoice.setAttendant(gate.getParkingAttendant());
        invoice.setExitTime(exitTime);
        invoice.setAmount(amount);
        return this.invoiceRepo.save(invoice);
    }

    private double calculateAdditionalServiceAmount(List<AdditionalService> servicesChosen) {
        double amount = 0;
        for(AdditionalService service: servicesChosen) {
            amount += service.getCost();
        }
        return amount;
    }
}
