package uz.doston.invoicetask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.doston.invoicetask.dto.product.BulkProductDto;
import uz.doston.invoicetask.dto.product.HighDemandProductDto;
import uz.doston.invoicetask.dto.product.ProductInYearDto;
import uz.doston.invoicetask.entity.Product;
import uz.doston.invoicetask.service.ProductService;

import java.util.List;

@RestController
@RequestMapping
public class ProductController extends AbstractController<ProductService> {

    public ProductController(ProductService service) {
        super(service);
    }

    @GetMapping(value = "/high_demand_products")
    public ResponseEntity<List<HighDemandProductDto>> highDemandProducts() {
        return service.highDemandProducts();
    }

    @GetMapping(value = "/bulk_products")
    public ResponseEntity<List<BulkProductDto>> bulkProducts() {
        return service.bulkProducts();
    }

    @GetMapping(value = "/number_of_products_in_year")
    public ResponseEntity<List<ProductInYearDto>> numberOfProductsInYear() {
        return service.numberOfProductsInYear();
    }

    @GetMapping(value = "/product/list")
    public ResponseEntity<List<Product>> list() {
        return service.getAll();
    }

    @GetMapping(value = "/product/details/{product_id}")
    public ResponseEntity<Product> details(@PathVariable("product_id") Integer productId) {
        return service.getByProductId(productId);
    }

}
