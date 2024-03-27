package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.Entities.Payment;
import com.XYZStrore.apiVentaProductos.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findByDatePaymentBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    @Query("SELECT p FROM payments p WHERE p.order.id = :orderId AND p.paymentMethod = :paymentMethod")
    List<Payment> findByOrderAndPaymentMethod(Long orderId, PaymentMethod paymentMethod);


}
