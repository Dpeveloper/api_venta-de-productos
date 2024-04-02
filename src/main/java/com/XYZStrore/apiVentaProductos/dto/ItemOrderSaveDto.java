package com.XYZStrore.apiVentaProductos.dto;

import java.math.BigDecimal;

public record ItemOrderSaveDto(Integer quantity, BigDecimal unitPrice) {
}
