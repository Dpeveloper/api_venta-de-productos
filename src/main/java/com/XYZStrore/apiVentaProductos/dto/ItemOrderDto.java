package com.XYZStrore.apiVentaProductos.dto;

import java.math.BigDecimal;

public record ItemOrderDto(Long id, Integer quantity, BigDecimal unitPrice) {
}
