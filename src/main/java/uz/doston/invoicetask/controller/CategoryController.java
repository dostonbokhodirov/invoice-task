package uz.doston.invoicetask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.doston.invoicetask.entity.Category;
import uz.doston.invoicetask.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController extends AbstractController<CategoryService> {

    public CategoryController(CategoryService service) {
        super(service);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Category>> list() {
        return service.getAll();
    }

    @GetMapping(value = "/{product_id}")
    public ResponseEntity<Category> list(@PathVariable("product_id") Integer productId) {
        return service.getByProductId(productId);
    }
}
