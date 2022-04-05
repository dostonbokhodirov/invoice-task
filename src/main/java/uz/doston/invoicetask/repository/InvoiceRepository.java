package uz.doston.invoicetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.doston.invoicetask.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "select * from public.invoice where issued > due", nativeQuery = true)
    Optional<List<Invoice>> findAllExpired();

    @Query(value = "select i.* from public.invoice i where issued < due", nativeQuery = true)
    Optional<List<Invoice>> findAllWrongDateInvoices();

    @Query(
            value = "select  i.id, cast(sum(p.amount) - i.amount as double precision) " +
                    "from public.invoice i " +
                    "inner join public.payment p on i.id = p.inv_id " +
                    "where i.amount < p.amount group by i.id",
            nativeQuery = true)
    Optional<List<Object[]>> findAllOverpaidInvoices();

    @Query(
            value = "select cast((select array_to_json(array_agg(row_to_json(my_table))) " +
                    "from (select  i.id, cast(sum(p.amount) - i.amount as double precision) " +
                    "from public.invoice i " +
                    "inner join public.payment p on i.id = p.inv_id " +
                    "where i.amount < p.amount group by i.id) as my_table) as text)",
            nativeQuery = true)
    String findAllOverpaidInvoicesJson();
}
