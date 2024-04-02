package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.AbstractDBTest;
import com.XYZStrore.apiVentaProductos.entities.ItemOrder;
import com.XYZStrore.apiVentaProductos.entities.Order;
import com.XYZStrore.apiVentaProductos.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


class ItemOrderRepositoryTest extends AbstractDBTest {

    ItemOrderRepository itemOrderRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;
    @Autowired
    public ItemOrderRepositoryTest(ItemOrderRepository itemOrderRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.itemOrderRepository = itemOrderRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    @BeforeEach
    void setUp() {
        ItemOrder itemOrder;
        Product product;

        product = Product.builder()
                .id(1L)
                .name("test")
                .price(new BigDecimal(10000))
                .stock(10)
                .build();
        Order order = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.now())
                .build();
        itemOrder = ItemOrder.builder()
                .id(1L)
                .quantity(20)
                .unitPrice(new BigDecimal(10000))
                .order(order)
                .product(product)
                .build();
        productRepository.save(product);
        orderRepository.save(order);
        itemOrderRepository.save(itemOrder);
        LinkedList<ItemOrder> itemOrders = new LinkedList<>();
        itemOrders.add(itemOrder);
        order.setItemOrder(itemOrders);
        product.setItemOrder(itemOrders);


    }

    @Test
    void givenItemOrder_whenSave_thenReturnItemOrder(){
        ItemOrder itemOrder = ItemOrder.builder()
                .id(2L)
                .quantity(20)
                .unitPrice(new BigDecimal(10000))
                .build();

        ItemOrder itemOrderSave = itemOrderRepository.save(itemOrder);

        assertThat(itemOrderSave).isNotNull();
        assertThat(itemOrderSave.getId()).isEqualTo(2L);
    }
    @Test
    void givenItemOrder_whenFindById_thenReturnItemOrderFound(){
        Long itemOrderId = 1L;
        Optional<ItemOrder> itemOrder = itemOrderRepository.findById(itemOrderId);

        assertThat(itemOrder).isPresent();
        assertThat(itemOrder.get().getId()).isEqualTo(itemOrderId);
        assertThat(itemOrder.get().getQuantity()).isEqualTo(20);
    }

    @Test
    void givenItemOrder_whenFindByOrderId_thenReturnItemOrderFound(){
        Long order = 1L;

        List<ItemOrder> itemOrdersFind = itemOrderRepository.findByOrderId(order);

        assertThat(itemOrdersFind).hasSize(1);
        assertThat(itemOrdersFind.get(0)).isNotNull();
        assertThat(itemOrdersFind.get(0).getOrder().getId()).isEqualTo(1L);
    }
    @Test
    void givenItemOrder_whenFindByProductId_thenReturnItemOrderFound(){
        Long product = 1L;

        List<ItemOrder> itemOrdersFind = itemOrderRepository.findByProductId(product);

        assertThat(itemOrdersFind).hasSize(1);
        assertThat(itemOrdersFind.get(0)).isNotNull();
        assertThat(itemOrdersFind.get(0).getOrder().getId()).isEqualTo(1L);
    }

    @Test
    void givenItemOrder_whenGetTotalSalesAmountForProduct_thenReturn(){
        Long productId = 1L;
        BigDecimal expectedAmount = new BigDecimal("200000.0");
        BigDecimal actualAmount = itemOrderRepository.getTotalSalesAmountForProduct(productId);

        assertEquals(0, expectedAmount.compareTo(actualAmount));
    }
    @Test
    void givenItemOrder_whenDeleteById(){
        Long itemOrderId = 1L;
        itemOrderRepository.deleteById(itemOrderId);

        Optional<ItemOrder> itemOrderVerify = itemOrderRepository.findById(itemOrderId);

        assertThat(itemOrderVerify).isNotPresent();
    }
}
