package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.PaymentDto;
import com.XYZStrore.apiVentaProductos.dto.PaymentSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment paymentSaveDtoToPayment(PaymentSaveDto paymentSaveDto);
    PaymentDto paymentToPaymentDto(Payment payment);
}
