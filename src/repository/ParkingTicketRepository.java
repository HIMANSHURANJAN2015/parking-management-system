package repository;

import model.ParkingTicket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingTicketRepository {
    private Map<Long, ParkingTicket> ticketMap = new HashMap<>();
    private static int id = 1;

    public ParkingTicket save(ParkingTicket ticket){
        if(ticket.getId() == 0) {
            ticket.setId(id++);
        }
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }

    public Optional<ParkingTicket> getTicketById(long ticketId){
        return Optional.ofNullable(ticketMap.get(ticketId));
    }
}
