package com.XYZStrore.apiVentaProductos.dto;

import com.XYZStrore.apiVentaProductos.enumdetail.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto(BigDecimal fullPayment, LocalDateTime datePayment, PaymentMethod paymentMethod) {
}
