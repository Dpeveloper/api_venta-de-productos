package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.PaymentDto;
import com.XYZStrore.apiVentaProductos.dto.PaymentSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Payment;
import com.XYZStrore.apiVentaProductos.enumdetail.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T01:30:52-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment paymentSaveDtoToPayment(PaymentSaveDto paymentSaveDto) {
        if ( paymentSaveDto == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.fullPayment( paymentSaveDto.fullPayment() );
        payment.datePayment( paymentSaveDto.datePayment() );
        payment.paymentMethod( paymentSaveDto.paymentMethod() );

        return payment.build();
    }

    @Override
    public PaymentDto paymentToPaymentDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        Long id = null;
        BigDecimal fullPayment = null;
        LocalDateTime datePayment = null;
        PaymentMethod paymentMethod = null;

        id = payment.getId();
        fullPayment = payment.getFullPayment();
        datePayment = payment.getDatePayment();
        paymentMethod = payment.getPaymentMethod();

        PaymentDto paymentDto = new PaymentDto( id, fullPayment, datePayment, paymentMethod );

        return paymentDto;
    }
}
