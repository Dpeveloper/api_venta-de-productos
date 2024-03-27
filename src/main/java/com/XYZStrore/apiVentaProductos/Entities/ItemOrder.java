package com.XYZStrore.apiVentaProductos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "ItemOrders")
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    public ItemOrder() {
    }
}
