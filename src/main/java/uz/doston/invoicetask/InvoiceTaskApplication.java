package uz.doston.invoicetask;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class InvoiceTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceTaskApplication.class, args);
    }

}
