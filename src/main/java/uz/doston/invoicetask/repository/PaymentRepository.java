package uz.doston.invoicetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.invoicetask.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
