package uz.doston.invoicetask.dto.payment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.doston.invoicetask.entity.Payment;

@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentResponseDto {
    public String paymentStatus;
    public Payment paymentDetails;

    public PaymentResponseDto(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
