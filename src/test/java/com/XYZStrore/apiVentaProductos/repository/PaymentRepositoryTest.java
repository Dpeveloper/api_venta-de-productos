package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.AbstractDBTest;
import com.XYZStrore.apiVentaProductos.entities.Order;
import com.XYZStrore.apiVentaProductos.entities.Payment;
import com.XYZStrore.apiVentaProductos.enumdetail.PaymentMethod;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class PaymentRepositoryTest extends AbstractDBTest {

    PaymentRepository paymentRepository;
    OrderRepository orderRepository;
    @Autowired
    public PaymentRepositoryTest(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }
    Payment payment;
    Order order;
    @BeforeEach
    void setUp(){
        order = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.now())
                .status(Status.ENTREGADO)
                .build();

        payment = Payment.builder()
                .id(1L)
                .fullPayment(new BigDecimal(10000))
                .datePayment(LocalDateTime.now())
                .paymentMethod(PaymentMethod.DAVIPLATA)
                .order(order)
                .build();
        order.setPayment(payment);
        paymentRepository.save(payment);
        orderRepository.save(order);
    }

    @Test
    void givenPayment_whenSave_thenReturnPayment(){
        Payment payment = Payment.builder()
                .id(2L)
                .fullPayment(new BigDecimal(10000))
                .datePayment(LocalDateTime.now())
                .paymentMethod(PaymentMethod.DAVIPLATA)
                .build();

        Payment PaymentSave = paymentRepository.save(payment);

        assertThat(PaymentSave).isNotNull();
        assertThat(PaymentSave.getId()).isEqualTo(2L);
    }
    @Test
    void givenPayment_whenFindById_thenReturnPaymentFound(){
        Long paymentId = 1L;
        Optional<Payment> paymentFind = paymentRepository.findById(paymentId);

        assertThat(paymentFind).isPresent();
        assertThat(paymentFind.get().getId()).isEqualTo(paymentId);
        assertThat(paymentFind.get().getPaymentMethod()).isEqualTo(PaymentMethod.DAVIPLATA);
    }

    @Test
    void givenOrder_whenFindByOrderDateBetween_thenReturnPaymentFound(){
        LocalDateTime dateTime1 = LocalDateTime.now().minusHours(1);
        LocalDateTime dateTime2 = LocalDateTime.now().plusHours(1);

        List<Payment> paymentsFound = paymentRepository.findByDatePaymentBetween(dateTime1,dateTime2);

        assertThat(paymentsFound).isNotNull();
        assertThat(paymentsFound).hasSize(1);
        assertThat(paymentsFound.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void givenOrder_whenFindByOrderIdAndPaymentMethod_thenReturnPaymentFound(){
        Long orderId = 1L;

        List<Payment> paymentsFound = paymentRepository.findByOrderIdAndPaymentMethod(orderId,PaymentMethod.DAVIPLATA);
        assertThat(paymentsFound).isNotNull();
        assertThat(paymentsFound).hasSize(1);
        assertThat(paymentsFound.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void givenPayment_whenDeleteById(){
        Long paymentId = 1L;
        paymentRepository.deleteById(paymentId);

        Optional<Payment> paymentVerify = paymentRepository.findById(paymentId);

        assertThat(paymentVerify).isNotPresent();
    }
}