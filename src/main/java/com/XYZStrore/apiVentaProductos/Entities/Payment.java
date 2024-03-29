package com.XYZStrore.apiVentaProductos.Entities;

import com.XYZStrore.apiVentaProductos.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    private BigDecimal fullPayment;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datePayment;
    private PaymentMethod paymentMethod;

    public Payment() {
    }
    @OneToOne(mappedBy = "payment")
    private Order order;
}
