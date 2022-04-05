package uz.doston.invoicetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.doston.invoicetask.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * choose product name as a code
     */
    @Query(
            value = "select p.name, count(d.pr_id) as total " +
                    "from public.product p " +
                    "inner join public.detail d on p.id = d.pr_id " +
                    "group by d.pr_id, p.name " +
                    "having count(d.pr_id) > 10",
            nativeQuery = true)
    Optional<List<Object[]>> findAllHighDemandProducts();

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select p.name, count(d.pr_id) as total " +
                    "from public.product p " +
                    "inner join public.detail d on p.id = d.pr_id " +
                    "group by d.pr_id, p.name " +
                    "having count(d.pr_id) > 10) as my_table) as text)",
            nativeQuery = true)
    String findAllHighDemandProductsJson();

    @Query(
            value = "select p.name, cast(p.price as double precision) " +
                    "from public.product p " +
                    "inner join public.detail d on p.id = d.pr_id " +
                    "group by p.name, p.price " +
                    "having count(d.pr_id) >= 2",
            nativeQuery = true)
    Optional<List<Object[]>> findAllBulkProducts();

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select p.name, cast(p.price as double precision) " +
                    "from public.product p " +
                    "inner join public.detail d on p.id = d.pr_id " +
                    "group by p.name, p.price " +
                    "having count(d.pr_id) >= 2) as my_table) as text)",
            nativeQuery = true)
    String findAllBulkProductsJson();

    @Query(
            value = "select c.country, count(o.cust_id) as total " +
                    "from public.customer c " +
                    "inner join public.orders o on c.id = o.cust_id " +
                    "where o.date  between '2016-01-01' and '2016-12-31' " +
                    "group by c.country",
            nativeQuery = true)
    Optional<List<Object[]>> findAllNumberOfProductsInYear();

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select c.country, count(o.cust_id) as total " +
                    "from public.customer c " +
                    "inner join public.orders o on c.id = o.cust_id " +
                    "where o.date  between '2016-01-01' and '2016-12-31' " +
                    "group by c.country) as my_table) as text)",
            nativeQuery = true)
    String findAllNumberOfProductsInYearJson();

}
