package com.XYZStrore.apiVentaProductos.dto;

import java.math.BigDecimal;

public record ProductSaveDto(String name, BigDecimal price, Integer stock) {
}
