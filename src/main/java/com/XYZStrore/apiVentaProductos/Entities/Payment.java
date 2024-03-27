package com.XYZStrore.apiVentaProductos.Entities;

import com.XYZStrore.apiVentaProductos.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double fullPayment;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datePayment;
    private PaymentMethod paymentMethod;

    public Payment() {
    }
    @OneToOne
    private Order order;
}
