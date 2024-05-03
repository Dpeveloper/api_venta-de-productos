package com.XYZStrore.apiVentaProductos.dto;

import com.XYZStrore.apiVentaProductos.enumdetail.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentSaveDto(BigDecimal fullPayment, LocalDateTime datePayment, PaymentMethod paymentMethod) {
}
