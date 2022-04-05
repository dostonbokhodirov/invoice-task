package uz.doston.invoicetask.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.doston.invoicetask.entity.Category;
import uz.doston.invoicetask.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends AbstractService<CategoryRepository> {

    protected CategoryService(CategoryRepository repository) {
        super(repository);
    }

    public ResponseEntity<List<Category>> getAll() {
        List<Category> categoryList = repository.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    public ResponseEntity<Category> getByProductId(Integer productId) {
        Optional<Category> optional = repository.findByProductId(productId);
        return optional
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
