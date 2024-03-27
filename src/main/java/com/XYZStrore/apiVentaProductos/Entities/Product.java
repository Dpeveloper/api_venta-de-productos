package com.XYZStrore.apiVentaProductos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer Stock;

    @OneToMany(mappedBy = "product")
    private List<ItemOrder> itemOrder;

    public Product() {
    }
}
