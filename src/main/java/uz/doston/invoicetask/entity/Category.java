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
public class Category extends Auditable {

    @Column(columnDefinition = "varchar(250)")
    private String name;

}
