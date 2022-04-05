package uz.doston.invoicetask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.doston.invoicetask.dto.order.*;
import uz.doston.invoicetask.service.OrderService;

import java.util.List;

@RestController
@RequestMapping
public class OrderController extends AbstractController<OrderService> {

    public OrderController(OrderService service) {
        super(service);
    }

    @GetMapping(value = "/orders_without_details")
    public ResponseEntity<List<OrderDto>> ordersWithoutDetails() {
        return service.ordersWithoutDetails();
    }

    @GetMapping(value = "/orders_without_invoices")
    public ResponseEntity<List<OrderWithoutInvoiceDto>> ordersWithoutInvoices() {
        return service.ordersWithoutInvoices();
    }

    @PostMapping(value = "/order")
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping(value = "/order/details/{order_id}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable("order_id") Integer orderId) {
        return service.getDetails(orderId);
    }

}
