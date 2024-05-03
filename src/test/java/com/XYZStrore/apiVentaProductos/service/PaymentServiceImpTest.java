package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.PaymentDto;
import com.XYZStrore.apiVentaProductos.dto.PaymentSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Payment;
import com.XYZStrore.apiVentaProductos.enumdetail.PaymentMethod;
import com.XYZStrore.apiVentaProductos.mapper.PaymentMapper;
import com.XYZStrore.apiVentaProductos.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImpTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentServiceImp paymentService;

    Payment payment;
    PaymentDto paymentDto;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setId(1L);
        payment.setFullPayment(new BigDecimal("100.00"));
        payment.setDatePayment(LocalDateTime.now());
        payment.setPaymentMethod(PaymentMethod.TARJETA_CREDITO);

        paymentDto = new PaymentDto(1L, new BigDecimal("100.00"), LocalDateTime.now(), PaymentMethod.TARJETA_CREDITO);
    }

    @Test
    void givenPaymentService_whenSavePayment_thenReturnSavedPayment() {
        when(paymentRepository.save(any())).thenReturn(payment);

        PaymentSaveDto paymentToSave = new PaymentSaveDto(new BigDecimal("100.00"), LocalDateTime.now(), PaymentMethod.TARJETA_CREDITO);

        when(paymentMapper.paymentToPaymentDto(any())).thenReturn(paymentDto);

        PaymentDto savedPayment = paymentService.savePayment(paymentToSave);

        assertThat(savedPayment).isNotNull();
    }

    @Test
    void givenPaymentService_whenFindPaymentById_thenReturnFoundPayment() {
        when(paymentRepository.findById(any())).thenReturn(Optional.of(payment));

        when(paymentMapper.paymentToPaymentDto(any())).thenReturn(paymentDto);

        PaymentDto foundPayment = paymentService.findPaymentById(1L);

        assertThat(foundPayment).isNotNull();
    }

    @Test
    void givenPaymentService_whenUpdatePayment_thenReturnUpdatedPayment() {
        when(paymentRepository.findById(any())).thenReturn(Optional.of(payment));

        PaymentSaveDto paymentToUpdate = new PaymentSaveDto(new BigDecimal("200.00"), LocalDateTime.now(), PaymentMethod.TARJETA_CREDITO);

        when(paymentRepository.save(any())).thenReturn(payment);

        when(paymentMapper.paymentToPaymentDto(any())).thenReturn(paymentDto);

        PaymentDto updatedPayment = paymentService.updatePayment(1L, paymentToUpdate);

        assertThat(updatedPayment).isNotNull();
    }

    @Test
    void givenPaymentService_whenDeletePayment_thenVoid() {
        Long paymentId = 1L;
        when(paymentRepository.findById(any())).thenReturn(Optional.of(payment));
        paymentService.deletePaymentById(paymentId);

        verify(paymentRepository, times(1)).deleteById(any());
    }

    @Test
    void givenPaymentService_whenGetAllPayments_thenReturnListOfPayment() {
        List<Payment> payments = List.of(payment);

        when(paymentRepository.findAll()).thenReturn(payments);

        List<PaymentDto> paymentDtos = paymentService.findAllPayments();

        assertThat(paymentDtos).isNotEmpty();
        assertThat(paymentDtos).hasSize(1);
    }
}