package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.OrderDto;
import com.XYZStrore.apiVentaProductos.dto.OrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import com.XYZStrore.apiVentaProductos.entities.Order;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import com.XYZStrore.apiVentaProductos.exception.OrderNotFoundException;
import com.XYZStrore.apiVentaProductos.mapper.OrderMapper;
import com.XYZStrore.apiVentaProductos.repository.OrderRepository;
import com.XYZStrore.apiVentaProductos.service.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderServiceImp(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto saveOrder(OrderSaveDto orderSaveDto) {
        Order order = orderMapper.orderSaveDtoToOrder(orderSaveDto);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderDto(savedOrder);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderSaveDto orderSaveDto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            order.setOrderDate(orderSaveDto.orderDate());
            order.setStatus(orderSaveDto.status());

            Order updatedOrder = orderRepository.save(order);
            return orderMapper.orderToOrderDto(updatedOrder);
        } else {
            throw new OrderNotFoundException("No se encontró la orden con el ID: " + id);
        }
    }
    @Override
    public void deleteOrder(Long id) {
        orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> findByOrderDateBetween(LocalDateTime orderDate, LocalDateTime orderDate2) {
        List<Order> orders = orderRepository.findByOrderDateBetween(orderDate, orderDate2);
        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findOrderByCostumerId(Long id) {
        List<Order> orders = orderRepository.findByCustomerWithOrderItems(id);
        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findByCustomerAndStatus(Customer customer, Status status) {
        List<Order> orders = orderRepository.findByCustomerAndStatus(customer, status);
        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }
}
