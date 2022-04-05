package uz.doston.invoicetask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Invoice extends Auditable {

    @OneToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    @Column(columnDefinition = "numeric", nullable = false)
    private Double amount;

    @Column(columnDefinition = "date", nullable = false)
    private Date issued;

    @Column(columnDefinition = "date", nullable = false)
    private Date due;

    public Invoice(Order order) {
        this(order, (double) 0, new Date(), new Date());

    }

    public Invoice(Order order, Double amount, Date issued, Date due) {
        this.order = order;
        this.amount = amount;
        this.issued = issued;
        this.due = due;
    }
}
