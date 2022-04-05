package uz.doston.invoicetask.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.doston.invoicetask.dto.invoice.InvoiceDto;
import uz.doston.invoicetask.dto.invoice.InvoiceOverpaidDto;
import uz.doston.invoicetask.entity.Invoice;
import uz.doston.invoicetask.entity.Order;
import uz.doston.invoicetask.repository.InvoiceRepository;

import java.util.*;

@Service
public class InvoiceService extends AbstractService<InvoiceRepository> {

    protected InvoiceService(InvoiceRepository repository) {
        super(repository);
    }

    public ResponseEntity<List<Invoice>> expiredInvoices() {
        Optional<List<Invoice>> optionalInvoices = repository.findAllExpired();
        return optionalInvoices.map(invoices -> new ResponseEntity<>(invoices, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<InvoiceDto>> wrongDateInvoices() {
        Optional<List<Invoice>> optionalInvoices = repository.findAllWrongDateInvoices();
        if (optionalInvoices.isPresent()) {
            List<Invoice> invoices = optionalInvoices.get();
            List<InvoiceDto> invoiceDtoList = new ArrayList<>(Collections.emptyList());
            invoices
                    .stream()
                    .map(invoice -> new InvoiceDto(invoice.getId(), invoice.getIssued(), invoice.getOrder().getId()))
                    .forEach(invoiceDtoList::add);
            return new ResponseEntity<>(invoiceDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<InvoiceOverpaidDto>> overpaidInvoices() {
        Optional<List<Object[]>> optional = repository.findAllOverpaidInvoices();
        List<InvoiceOverpaidDto> dtoList = new ArrayList<>(Collections.emptyList());
        if (optional.isPresent()) {
            for (Object[] objects : optional.get()) {
                InvoiceOverpaidDto dto = new InvoiceOverpaidDto((Integer) objects[0], (Double) objects[1]);
                dtoList.add(dto);
            }
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * method using object mapper
     */
    public ResponseEntity<List<InvoiceOverpaidDto>> overpaidInvoicesJson() {
        String json = repository.findAllOverpaidInvoicesJson();
        List<InvoiceOverpaidDto> dtoList = getResponse(json);
        return Objects.isNull(dtoList)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    public Integer createByOrder(Order order) {
        Invoice invoice = new Invoice(order);
        return repository.save(invoice).getId();
    }


}
