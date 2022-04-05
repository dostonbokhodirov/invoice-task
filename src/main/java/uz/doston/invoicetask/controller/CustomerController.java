package uz.doston.invoicetask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.doston.invoicetask.dto.customer.CustomerDto;
import uz.doston.invoicetask.entity.Customer;
import uz.doston.invoicetask.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping
public class CustomerController extends AbstractController<CustomerService> {

    public CustomerController(CustomerService service) {
        super(service);
    }

    @GetMapping(value = "/customers_without_orders")
    public ResponseEntity<List<Customer>> customersWithoutOrders() {
        return service.customersWithoutOrders();
    }

    @GetMapping(value = "/customers_last_orders")
    public ResponseEntity<List<CustomerDto>> customersLastOrders() {
        return service.customersLastOrders();
    }


}
