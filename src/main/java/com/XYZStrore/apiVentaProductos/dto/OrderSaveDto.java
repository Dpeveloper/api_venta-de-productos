package com.XYZStrore.apiVentaProductos.dto;

import com.XYZStrore.apiVentaProductos.enumdetail.Status;

import java.time.LocalDateTime;

public record OrderSaveDto(LocalDateTime orderDate, Status status) {
}
