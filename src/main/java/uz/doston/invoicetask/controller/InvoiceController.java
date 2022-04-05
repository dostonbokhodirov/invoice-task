package uz.doston.invoicetask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.doston.invoicetask.dto.invoice.InvoiceDto;
import uz.doston.invoicetask.dto.invoice.InvoiceOverpaidDto;
import uz.doston.invoicetask.entity.Invoice;
import uz.doston.invoicetask.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping
public class InvoiceController extends AbstractController<InvoiceService> {

    public InvoiceController(InvoiceService service) {
        super(service);
    }

    @GetMapping(value = "/expired_invoices")
    public ResponseEntity<List<Invoice>> expiredInvoices() {
        return service.expiredInvoices();
    }

    @GetMapping(value = "/wrong_date_invoices")
    public ResponseEntity<List<InvoiceDto>> wrongDateInvoices() {
        return service.wrongDateInvoices();
    }

    @GetMapping(value = "/overpaid_invoices")
    public ResponseEntity<List<InvoiceOverpaidDto>> overpaidInvoices() {
        return service.overpaidInvoices();
    }

}
