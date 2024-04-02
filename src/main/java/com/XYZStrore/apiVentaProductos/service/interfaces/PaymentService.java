package com.XYZStrore.apiVentaProductos.service.interfaces;

import com.XYZStrore.apiVentaProductos.dto.PaymentDto;
import com.XYZStrore.apiVentaProductos.dto.PaymentSaveDto;
import com.XYZStrore.apiVentaProductos.enumdetail.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    PaymentDto savePayment(PaymentSaveDto paymentSaveDto);
    PaymentDto findPaymentById(Long id);
    PaymentDto updatePayment(Long id, PaymentSaveDto paymentSaveDto);
    void deletePaymentById(Long id);
    List<PaymentDto> findByDatePaymentBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);
    List<PaymentDto> findByOrderIdAndPaymentMethod(Long orderId, PaymentMethod paymentMethod);
}
