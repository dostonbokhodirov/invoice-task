package uz.doston.invoicetask.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.doston.invoicetask.dto.product.BulkProductDto;
import uz.doston.invoicetask.dto.product.HighDemandProductDto;
import uz.doston.invoicetask.dto.product.ProductInYearDto;
import uz.doston.invoicetask.entity.Product;
import uz.doston.invoicetask.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService extends AbstractService<ProductRepository> {

    protected ProductService(ProductRepository repository) {
        super(repository);
    }

    public ResponseEntity<List<BulkProductDto>> bulkProducts() {
        Optional<List<Object[]>> optional = repository.findAllBulkProducts();
        List<BulkProductDto> dtoList = new ArrayList<>(Collections.emptyList());
        if (optional.isPresent()) {
            optional.get()
                    .stream()
                    .map(objects -> new BulkProductDto((String) objects[0], (Double) objects[1]))
                    .forEach(dtoList::add);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<ProductInYearDto>> numberOfProductsInYear() {
        Optional<List<Object[]>> optional = repository.findAllNumberOfProductsInYear();
        List<ProductInYearDto> dtoList = new ArrayList<>(Collections.emptyList());
        if (optional.isPresent()) {
            optional.get()
                    .stream()
                    .map(objects -> new ProductInYearDto((Integer) objects[0], (String) objects[1]))
                    .forEach(dtoList::add);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = repository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    public ResponseEntity<Product> getByProductId(Integer productId) {
        Optional<Product> optional = repository.findById(productId);
        return optional
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<HighDemandProductDto>> highDemandProducts() {
        Optional<List<Object[]>> optional = repository.findAllHighDemandProducts();
        List<HighDemandProductDto> dtoList = new ArrayList<>(Collections.emptyList());
        if (optional.isPresent()) {
            optional.get()
                    .stream()
                    .map(objects -> new HighDemandProductDto((String) objects[0], (Integer) objects[1]))
                    .forEach(dtoList::add);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    /**
     * some methods using object mapper
     */
    public ResponseEntity<List<BulkProductDto>> bulkProductsJson() {
        String json = repository.findAllBulkProductsJson();
        List<BulkProductDto> dtoList = getResponse(json);
        return Objects.isNull(dtoList)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductInYearDto>> numberOfProductsInYearJson() {
        String json = repository.findAllNumberOfProductsInYearJson();
        List<ProductInYearDto> dtoList = getResponse(json);
        return Objects.isNull(dtoList)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    public ResponseEntity<List<HighDemandProductDto>> highDemandProductsJson() {
        String json = repository.findAllHighDemandProductsJson();
        List<HighDemandProductDto> dtoList = getResponse(json);
        return Objects.isNull(dtoList)
                ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(dtoList, HttpStatus.OK);

    }
}
