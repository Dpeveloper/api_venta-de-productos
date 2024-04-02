package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.PaymentDto;
import com.XYZStrore.apiVentaProductos.dto.PaymentSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Payment;
import com.XYZStrore.apiVentaProductos.exception.PaymentNotFoundException;
import com.XYZStrore.apiVentaProductos.mapper.PaymentMapper;
import com.XYZStrore.apiVentaProductos.repository.PaymentRepository;
import com.XYZStrore.apiVentaProductos.service.interfaces.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImp implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    public PaymentServiceImp(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDto savePayment(PaymentSaveDto paymentSaveDto) {
        Payment payment = paymentMapper.paymentSaveDtoToPayment(paymentSaveDto);
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.paymentToPaymentDto(savedPayment);
    }

    @Override
    public List<PaymentDto> findAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(paymentMapper::paymentToPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto findPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
        return paymentMapper.paymentToPaymentDto(payment);
    }

    @Override
    public PaymentDto updatePayment(Long id, PaymentSaveDto paymentSaveDto) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();

            payment.setFullPayment(paymentSaveDto.fullPayment());
            payment.setDatePayment(paymentSaveDto.datePayment());
            payment.setPaymentMethod(paymentSaveDto.paymentMethod());

            Payment updatedPayment = paymentRepository.save(payment);
            return paymentMapper.paymentToPaymentDto(updatedPayment);
        } else {
            throw new PaymentNotFoundException("No se encontró el pago con el ID: " + id);
        }
    }

    @Override
    public void deletePaymentById(Long id) {
        paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDto> findByDatePaymentBetween(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        List<Payment> payments = paymentRepository.findByDatePaymentBetween(dateTime1, dateTime2);
        return payments.stream()
                .map(paymentMapper::paymentToPaymentDto)
                .collect(Collectors.toList());
    }
    @Override
    public PaymentDto findByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        return paymentMapper.paymentToPaymentDto(payment);
    }
}
