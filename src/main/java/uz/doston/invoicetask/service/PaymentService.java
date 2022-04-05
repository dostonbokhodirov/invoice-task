package uz.doston.invoicetask.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.doston.invoicetask.dto.payment.PaymentResponseDto;
import uz.doston.invoicetask.entity.Invoice;
import uz.doston.invoicetask.entity.Payment;
import uz.doston.invoicetask.repository.InvoiceRepository;
import uz.doston.invoicetask.repository.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentService extends AbstractService<PaymentRepository> {

    protected PaymentService(PaymentRepository repository, InvoiceRepository invoiceRepository) {
        super(repository);
        this.invoiceRepository = invoiceRepository;
    }

    private final InvoiceRepository invoiceRepository;

    public ResponseEntity<PaymentResponseDto> create(Integer invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isEmpty()) {
            return new ResponseEntity<>(new PaymentResponseDto("FAILED"), HttpStatus.BAD_REQUEST);
        }
        Payment payment = new Payment(optionalInvoice.get());
        try {
            Payment save = repository.save(payment);
            return new ResponseEntity<>(new PaymentResponseDto("SUCCESS", save), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new PaymentResponseDto("FAILED"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Payment> get(Integer paymentId) {
        Optional<Payment> optional = repository.findById(paymentId);
        return optional
                .map(payment -> new ResponseEntity<>(payment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
