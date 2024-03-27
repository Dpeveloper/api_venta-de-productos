package com.XYZStrore.apiVentaProductos.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "ShippingDetailS")
public class ShippingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addres;
    private String conveyor;
    private Integer numberGuide;

    @OneToOne
    private Order order;
    public ShippingDetail() {
    }
}
