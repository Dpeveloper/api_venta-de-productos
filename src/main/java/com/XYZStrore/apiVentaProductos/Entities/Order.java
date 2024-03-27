package com.XYZStrore.apiVentaProductos.Entities;

import com.XYZStrore.apiVentaProductos.enums.Status;
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
@Entity(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<ItemOrder> itemOrder;

    @OneToOne
    private Payment payment;

    @OneToOne
    private ShippingDetail shippingDetail;

    public Order() {
    }
}
