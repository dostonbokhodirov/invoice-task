package uz.doston.invoicetask.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.doston.invoicetask.dto.customer.CustomerDto;
import uz.doston.invoicetask.entity.Customer;
import uz.doston.invoicetask.repository.CustomerRepository;

import java.util.*;

@Service
public class CustomerService extends AbstractService<CustomerRepository> {

    protected CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public ResponseEntity<List<CustomerDto>> customersLastOrders() {
        Optional<List<Object[]>> optional = repository.findAllByLastOrders();
        List<CustomerDto> customerDtoList = new ArrayList<>(Collections.emptyList());
        if (optional.isPresent()) {
            List<Object[]> objects = optional.get();
            for (Object[] object : objects) {
                CustomerDto dto = new CustomerDto((Integer) object[0], (String) object[1], (Date) object[2]);
                customerDtoList.add(dto);
            }
            return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    /**
     * method using object mapper
     */
    public ResponseEntity<List<CustomerDto>> customersLastOrdersJson() {
        String json = repository.findAllByLastOrdersJson();
        List<CustomerDto> customerDtoList = getResponse(json);
        return Objects.isNull(customerDtoList)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }


    public ResponseEntity<List<Customer>> customersWithoutOrders() {
        Optional<List<Customer>> optionalCustomers = repository.findAllWithoutOrders();
        return optionalCustomers.
                map(customers -> new ResponseEntity<>(customers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
