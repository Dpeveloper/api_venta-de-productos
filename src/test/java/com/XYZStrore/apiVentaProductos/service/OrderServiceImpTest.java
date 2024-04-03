package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.OrderDto;
import com.XYZStrore.apiVentaProductos.dto.OrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Order;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import com.XYZStrore.apiVentaProductos.mapper.OrderMapper;
import com.XYZStrore.apiVentaProductos.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImpTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImp orderService;

    Order order;
    OrderDto orderDto;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.ENTREGADO);

        orderDto = new OrderDto(1L, LocalDateTime.now(), Status.ENTREGADO);
    }

    @Test
    void givenOrderService_whenSaveOrder_thenReturnSavedOrder() {
        when(orderRepository.save(any())).thenReturn(order);

        OrderSaveDto orderToSave = new OrderSaveDto(LocalDateTime.now(), Status.ENTREGADO);

        when(orderMapper.orderToOrderDto(any())).thenReturn(orderDto);

        OrderDto savedOrder = orderService.saveOrder(orderToSave);

        assertThat(savedOrder).isNotNull();
    }

    @Test
    void givenOrderService_whenFindOrderById_thenReturnFoundOrder() {
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        when(orderMapper.orderToOrderDto(any())).thenReturn(orderDto);

        OrderDto foundOrder = orderService.findOrderById(1L);

        assertThat(foundOrder).isNotNull();
    }

    @Test
    void givenOrderService_whenUpdateOrder_thenReturnUpdatedOrder() {
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        OrderSaveDto orderToUpdate = new OrderSaveDto(LocalDateTime.now(), Status.ENTREGADO);

        when(orderRepository.save(any())).thenReturn(order);

        when(orderMapper.orderToOrderDto(any())).thenReturn(orderDto);

        OrderDto updatedOrder = orderService.updateOrder(1L, orderToUpdate);

        assertThat(updatedOrder).isNotNull();
    }

    @Test
    void givenOrderService_whenDeleteOrder_thenVoid() {
        Long orderId = 1L;
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(any());
    }

    @Test
    void givenOrderService_whenGetAllOrders_thenReturnListOfOrder() {
        List<Order> orders = List.of(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDto> orderDtos = orderService.findAllOrders();

        assertThat(orderDtos).isNotEmpty();
        assertThat(orderDtos).hasSize(1);
    }
}

