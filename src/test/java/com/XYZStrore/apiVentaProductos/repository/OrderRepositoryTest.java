package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.AbstractDBTest;
import com.XYZStrore.apiVentaProductos.Entities.Customer;
import com.XYZStrore.apiVentaProductos.Entities.ItemOrder;
import com.XYZStrore.apiVentaProductos.Entities.Order;
import com.XYZStrore.apiVentaProductos.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OrderRepositoryTest extends AbstractDBTest {

    OrderRepository orderRepository;
    CustomerRepository customerRepository;

    ItemOrderRepository itemOrderRepository;
    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository, CustomerRepository customerRepository, ItemOrderRepository itemOrderRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemOrderRepository = itemOrderRepository;
    }
    Order order;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(1L)
                .name("test")
                .email("@test")
                .address("street six")
                .build();

        order = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.now())
                .status(Status.ENTREGADO)
                .build();

        ItemOrder itemOrder = ItemOrder.builder()
                .id(1L)
                .quantity(20)
                .unitPrice(new BigDecimal(10000))
                .build();

        customerRepository.save(customer);
        orderRepository.save(order);
        itemOrderRepository.save(itemOrder);

        List<Order> orders = new LinkedList<>();
        orders.add(order);
        List<ItemOrder> itemOrders = new LinkedList<>();
        itemOrders.add(itemOrder);

        order.setCustomer(customer);
        customer.setOrders(orders);
        order.setItemOrder(itemOrders);
        itemOrder.setOrder(order);

        customerRepository.save(customer);
        orderRepository.save(order);
        itemOrderRepository.save(itemOrder);

    }

    @Test
    void givenOrder_whenSave_thenReturnOrder(){
        Order order = Order.builder()
                .id(2L)
                .orderDate(LocalDateTime.now())
                .status(Status.ENTREGADO)
                .build();

        Order OrderSave = orderRepository.save(order);

        assertThat(OrderSave).isNotNull();
        assertThat(OrderSave.getId()).isEqualTo(2L);
    }
    @Test
    void givenOrder_whenFindById_thenReturnOrderFound(){

        Long orderId = 1L;
        Optional<Order> orderFind = orderRepository.findById(orderId);

        assertThat(orderFind).isPresent();
        assertThat(orderFind.get().getId()).isEqualTo(orderId);
        assertThat(orderFind.get().getStatus()).isEqualTo(Status.ENTREGADO);
    }

    @Test
    void givenOrder_whenFindByOrderDateBetween_thenReturnOrderFound(){
        LocalDateTime dateTime1 = LocalDateTime.now().minusHours(1);
        LocalDateTime dateTime2 = LocalDateTime.now().plusHours(1);

        List<Order> orderFound = orderRepository.findByOrderDateBetween(dateTime1,dateTime2);

        assertThat(orderFound).isNotNull();
        assertThat(orderFound).hasSize(1);
        assertThat(orderFound.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void givenOrder_whenFindByCustomerAndStatus_thenReturnOrderFound(){
        List<Order> ordersFound = orderRepository.findByCustomerAndStatus(customer,Status.ENTREGADO);

        assertThat(ordersFound).hasSize(1);
        assertThat(ordersFound.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void givenOrder_whenFindByCustomerWithOrderItems_thenReturnOrders(){
        List<Order> ordersFound = orderRepository.findByCustomerWithOrderItems(customer);
        assertThat(ordersFound).hasSize(1);
        assertThat(ordersFound.get(0).getId()).isEqualTo(1L);
    }
    @Test
    void givenOrder_whenDeleteById(){
        Long orderId = 1L;
        orderRepository.deleteById(orderId);

        Optional<Order> customerVerify = orderRepository.findById(orderId);

        assertThat(customerVerify).isNotPresent();
    }
}