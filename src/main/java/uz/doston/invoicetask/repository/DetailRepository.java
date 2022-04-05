package uz.doston.invoicetask.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.invoicetask.entity.Detail;

import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, Integer> {

    Optional<Detail> findByOrderId(Integer orderId);

}
