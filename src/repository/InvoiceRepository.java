package repository;

import model.*;
import java.util.*;

public class InvoiceRepository {
    Map<Long, Invoice> invoiceMap = new HashMap<>();
    private long id = 1;

    public Invoice save(Invoice invoice) {
        if(invoice.getId()==0) {
            invoice.setId(id++);
        }
        invoiceMap.put(invoice.getId(), invoice);
        return invoice;
    }
}