package uz.doston.invoicetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.doston.invoicetask.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select o.* from public.orders o inner join public.detail d on d.ord_id <> o.id where o.date < '2016-09-06'",
            nativeQuery = true)
    Optional<List<Order>> findAllWithoutDetails();

    @Query(
            value = "select o.* from public.orders o " +
                    "inner join public.detail d on o.id = d.ord_id " +
                    "where o.id not in (select i.ord_id from public.invoice i)",
            nativeQuery = true)
    Optional<List<Order>> findAllByWithoutInvoices();

}
