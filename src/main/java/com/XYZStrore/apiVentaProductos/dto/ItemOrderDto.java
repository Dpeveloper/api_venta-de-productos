package com.XYZStrore.apiVentaProductos.dto;

import java.math.BigDecimal;

public record ItemOrderDto(Integer quantity, BigDecimal unitPrice) {
}
