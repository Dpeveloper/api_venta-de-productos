package com.XYZStrore.apiVentaProductos.entities;

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
@Entity(name = "item_orders")
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "id_itemOrder")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public ItemOrder() {
    }
}
