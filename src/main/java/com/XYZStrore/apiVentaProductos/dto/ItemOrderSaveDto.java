package com.XYZStrore.apiVentaProductos.dto;

import java.math.BigDecimal;

public record ItemOrderSaveDto(Long id, Integer quantity, BigDecimal unitPrice) {
}
