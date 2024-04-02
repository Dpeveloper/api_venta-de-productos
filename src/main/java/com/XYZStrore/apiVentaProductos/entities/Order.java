package com.XYZStrore.apiVentaProductos.entities;

import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<ItemOrder> itemOrder;

    @OneToOne
    @JoinColumn(name = "id_payment", referencedColumnName = "id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "id_shipping_detail", referencedColumnName = "id")
    private ShippingDetail shippingDetail;

    public Order() {
    }
}
