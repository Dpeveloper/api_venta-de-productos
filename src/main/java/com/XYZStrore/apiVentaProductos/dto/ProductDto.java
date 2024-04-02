package com.XYZStrore.apiVentaProductos.dto;

import java.math.BigDecimal;

public record ProductDto(String name, BigDecimal price, Integer stock) {
}
