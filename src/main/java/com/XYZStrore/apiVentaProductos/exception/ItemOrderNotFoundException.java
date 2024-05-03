package com.XYZStrore.apiVentaProductos.exception;

public class ItemOrderNotFoundException extends RuntimeException {
    public ItemOrderNotFoundException() {
    }

    public ItemOrderNotFoundException(String s) {
        super(s);
    }
}
