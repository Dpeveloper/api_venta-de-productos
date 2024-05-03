package com.XYZStrore.apiVentaProductos.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String s) {
        super(s);
    }
}
