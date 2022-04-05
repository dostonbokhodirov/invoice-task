package uz.doston.invoicetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.doston.invoicetask.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(
            value = "select c.* from public.customer c inner join public.orders o on c.id = o.cust_id where o.date not between '2016-01-01' and '2016-12-31' ",
            nativeQuery = true)
    Optional<List<Customer>> findAllWithoutOrders();

    @Query(
            value = "select distinct on (o.date) c.id, c.name, o.date " +
                    "from public.customer c " +
                    "inner join public.orders o on c.id = o.cust_id " +
                    "order by o.date desc",
            nativeQuery = true)
    Optional<List<Object[]>> findAllByLastOrders();

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select distinct on (o.date) c.id, c.name, o.date " +
                    "from public.customer c " +
                    "inner join public.orders o on c.id = o.cust_id " +
                    "order by o.date desc) my_table) as text)",
            nativeQuery = true)
    String findAllByLastOrdersJson();

}
