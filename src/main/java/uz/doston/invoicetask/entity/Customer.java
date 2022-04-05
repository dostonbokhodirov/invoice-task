package uz.doston.invoicetask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends Auditable {

    @Column(columnDefinition = "varchar(14)", nullable = false)
    private String name;

    @Column(columnDefinition = "char(3)", nullable = false)
    private String country;

    @Column(columnDefinition = "text")
    private String address;

    @Column(columnDefinition = "varchar(50)")
    private String phone;

}
