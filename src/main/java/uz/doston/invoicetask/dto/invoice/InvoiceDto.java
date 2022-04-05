package uz.doston.invoicetask.dto.invoice;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InvoiceDto {
    public Integer id;
    public Date date;
    public Integer ordId;
}
