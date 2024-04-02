package com.XYZStrore.apiVentaProductos.service.interfaces;

import com.XYZStrore.apiVentaProductos.dto.OrderDto;
import com.XYZStrore.apiVentaProductos.dto.OrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderDto saveOrder(OrderSaveDto orderSaveDto);
    List<OrderDto> findAllOrders();
    OrderDto findOrderById(Long id);
    OrderDto updateOrder(Long id, OrderSaveDto orderSaveDto);
    void deleteOrder(Long id);
    List<OrderDto> findByOrderDateRange(LocalDateTime orderDate, LocalDateTime orderDate2);
    List<OrderDto> findByCustomerAndStatus(Customer customer, Status status);
}
