package com.XYZStrore.apiVentaProductos.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String s) {
        super(s);
    }

    public PaymentNotFoundException() {
    }
}
