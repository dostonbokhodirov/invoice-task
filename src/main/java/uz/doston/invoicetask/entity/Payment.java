package uz.doston.invoicetask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Payment extends Auditable {

    @CreationTimestamp
    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime time;

    @Column(columnDefinition = "numeric", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "inv_id")
    private Invoice invoice;

    public Payment(Invoice invoice) {
        this(LocalDateTime.now(), (double) 0, invoice);

    }

    public Payment(LocalDateTime time, Double amount, Invoice invoice) {
        this.time = time;
        this.amount = amount;
        this.invoice = invoice;
    }
}
