package uz.doston.invoicetask.dto.order;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.doston.invoicetask.entity.Detail;
import uz.doston.invoicetask.entity.Order;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderWithoutInvoiceDto {
    public Integer orderId;
    public Date date;
    public Double totalPrice;

    public OrderWithoutInvoiceDto(Order order) {
        this.orderId = order.getId();
        this.date = order.getDate();
        this.totalPrice = getTotalPrice(order.getDetails());
    }

    private double getTotalPrice(List<Detail> details) {
        return details.stream()
                .mapToDouble(detail -> detail.getProduct().getPrice() * detail.getQuantity())
                .sum();
    }

}
