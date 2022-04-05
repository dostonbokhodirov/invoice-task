package uz.doston.invoicetask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.doston.invoicetask.dto.payment.PaymentResponseDto;
import uz.doston.invoicetask.entity.Payment;
import uz.doston.invoicetask.service.PaymentService;

@RestController
@RequestMapping
public class PaymentController extends AbstractController<PaymentService> {

    public PaymentController(PaymentService service) {
        super(service);
    }

    @PostMapping(value = "/payment")
    public ResponseEntity<PaymentResponseDto> create(@RequestBody Integer invoiceId) {
        return service.create(invoiceId);
    }

    @GetMapping(value = "/payment/details/{payment_details_id}")
    public ResponseEntity<Payment> details(@PathVariable("payment_details_id") Integer paymentId) {
        return service.get(paymentId);
    }

}
