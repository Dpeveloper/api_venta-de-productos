package com.XYZStrore.apiVentaProductos.controller;

import com.XYZStrore.apiVentaProductos.dto.PaymentDto;
import com.XYZStrore.apiVentaProductos.dto.PaymentSaveDto;
import com.XYZStrore.apiVentaProductos.service.interfaces.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(paymentService.findPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        return ResponseEntity.ok().body(paymentService.findAllPayments());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentDto> getPaymentsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(paymentService.findByOrderId(orderId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDto>> getPaymentsByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok().body(paymentService.findByDatePaymentBetween(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentSaveDto paymentSaveDto) {
        return ResponseEntity.ok().body(paymentService.savePayment(paymentSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id, @RequestBody PaymentSaveDto paymentSaveDto) {
        return ResponseEntity.ok().body(paymentService.updatePayment(id, paymentSaveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }
}
