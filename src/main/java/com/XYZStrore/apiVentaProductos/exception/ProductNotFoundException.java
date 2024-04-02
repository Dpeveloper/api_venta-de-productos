package com.XYZStrore.apiVentaProductos.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String s) {
        super(s);
    }
}
