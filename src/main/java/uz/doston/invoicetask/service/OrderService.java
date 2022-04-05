package uz.doston.invoicetask.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.doston.invoicetask.dto.order.*;
import uz.doston.invoicetask.entity.Customer;
import uz.doston.invoicetask.entity.Detail;
import uz.doston.invoicetask.entity.Order;
import uz.doston.invoicetask.entity.Product;
import uz.doston.invoicetask.repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends AbstractService<OrderRepository> {

    protected OrderService(OrderRepository repository, CustomerRepository customerRepository, InvoiceService invoiceService, DetailRepository detailRepository, ProductRepository productRepository) {
        super(repository);
        this.customerRepository = customerRepository;
        this.invoiceService = invoiceService;
        this.detailRepository = detailRepository;
        this.productRepository = productRepository;
    }

    private final CustomerRepository customerRepository;
    private final InvoiceService invoiceService;
    private final DetailRepository detailRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<List<OrderDto>> ordersWithoutDetails() {
        Optional<List<Order>> optionalOrders = repository.findAllWithoutDetails();
        if (optionalOrders.isPresent()) {
            List<OrderDto> orderDtoList = new ArrayList<>(Collections.emptyList());
            optionalOrders.get()
                    .stream()
                    .map(order -> new OrderDto(order.getId(), order.getDate(), order.getCustomer().getId()))
                    .forEach(orderDtoList::add);
            return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<OrderWithoutInvoiceDto>> ordersWithoutInvoices() {
        List<OrderWithoutInvoiceDto> dtoList = new ArrayList<>(Collections.emptyList());
        Optional<List<Order>> optionalOrders = repository.findAllByWithoutInvoices();
        if (optionalOrders.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        optionalOrders.get()
                .stream()
                .map(OrderWithoutInvoiceDto::new)
                .forEach(dtoList::add);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    public ResponseEntity<OrderResponseDto> create(OrderCreateDto dto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(dto.customerId);
        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(new OrderResponseDto("FAILED"), HttpStatus.BAD_REQUEST);
        }
        Optional<Product> optionalProduct = productRepository.findById(dto.productId);
        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>(new OrderResponseDto("FAILED"), HttpStatus.BAD_REQUEST);
        }
        Order savedOrder = repository.save(new Order(optionalCustomer.get()));
        detailRepository.save(new Detail(savedOrder, optionalProduct.get(), dto.quantity));
        Integer createdInvoiceId = invoiceService.createByOrder(savedOrder);
        return new ResponseEntity<>(new OrderResponseDto("SUCCESS", createdInvoiceId), HttpStatus.OK);
    }

    public ResponseEntity<OrderDetailsDto> getDetails(Integer orderId) {
        Optional<Order> optionalOrder = repository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Order order = optionalOrder.get();
        Optional<Detail> optionalDetail = detailRepository.findByOrderId(order.getId());
        if (optionalDetail.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        OrderDetailsDto dto = OrderDetailsDto
                .builder()
                .id(order.getId())
                .date(order.getDate())
                .customerId(order.getCustomer().getId())
                .productName(optionalDetail.get().getProduct().getName())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
