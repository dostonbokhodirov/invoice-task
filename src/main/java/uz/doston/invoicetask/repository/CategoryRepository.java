package uz.doston.invoicetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.doston.invoicetask.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(
            value = "select c.* " +
                    "from public.category c " +
                    "inner join public.product p on c.id = p.category_id " +
                    "where p.id = :product_id", nativeQuery = true)
    Optional<Category> findByProductId(@Param(value = "product_id") Integer productId);

}
