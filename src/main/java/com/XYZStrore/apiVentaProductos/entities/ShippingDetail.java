package com.XYZStrore.apiVentaProductos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "shippingDetailS")
public class ShippingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String conveyor;
    private Integer numberGuide;

    @OneToOne(mappedBy = "shippingDetail")
    private Order order;

    public ShippingDetail() {
    }
}
