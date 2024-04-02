package com.XYZStrore.apiVentaProductos.controller;

import com.XYZStrore.apiVentaProductos.dto.OrderDto;
import com.XYZStrore.apiVentaProductos.dto.OrderSaveDto;
import com.XYZStrore.apiVentaProductos.service.interfaces.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

        private final OrderService orderService;

        public OrderController(OrderService orderService) {
            this.orderService = orderService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
            return ResponseEntity.ok(orderService.findOrderById(id));
        }

        @GetMapping
        public ResponseEntity<List<OrderDto>> getAllOrders() {
            return ResponseEntity.ok(orderService.findAllOrders());
        }

        @GetMapping("/customer/{customerId}")
        public List<OrderDto> getOrdersByCustomerId(@PathVariable Long customerId) {
            return orderService.findOrdersByCustomer(customerId);
        }

        @GetMapping("/date-range")
        public ResponseEntity<List<OrderDto>> getOrdersByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
            return ResponseEntity.ok(orderService.findByOrderDateBetween(startDate, endDate));
        }

        @PostMapping
        public ResponseEntity<OrderDto> createOrder(@RequestBody OrderSaveDto orderSaveDto) {
            return ResponseEntity.ok(orderService.saveOrder(orderSaveDto));
        }

        @PutMapping("/{id}")
        public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderSaveDto orderSaveDto) {
            return ResponseEntity.ok(orderService.updateOrder(id, orderSaveDto));
        }
    }
