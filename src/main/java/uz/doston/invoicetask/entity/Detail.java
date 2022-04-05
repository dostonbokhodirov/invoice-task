package uz.doston.invoicetask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Detail extends Auditable {

    @ManyToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pr_id")
    private Product product;


    @Column(columnDefinition = "smallint",nullable = false)
    private Short quantity;

}
