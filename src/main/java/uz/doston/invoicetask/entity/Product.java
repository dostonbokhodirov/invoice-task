package uz.doston.invoicetask.entity;

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
@Entity
public class Product extends Auditable {

    @Column(columnDefinition = "varchar(10)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(columnDefinition = "varchar(20)")
    private String description;

    @Column(columnDefinition = "numeric")
    private Double price;

    @Column(columnDefinition = "varchar(1024)")
    private String  photo;

}
