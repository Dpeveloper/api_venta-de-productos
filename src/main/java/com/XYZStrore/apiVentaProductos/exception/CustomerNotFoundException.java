package com.XYZStrore.apiVentaProductos.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String s) {
        super(s);
    }
}
