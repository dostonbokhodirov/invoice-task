package uz.doston.invoicetask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends Auditable {

    @CreatedDate
    @Column(columnDefinition = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @OneToMany
    List<Detail> details;

    public Order(Customer customer) {
        this(new Date(), customer);
    }

    public Order(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

}
