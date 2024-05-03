package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.AbstractDBTest;
import com.XYZStrore.apiVentaProductos.entities.Order;
import com.XYZStrore.apiVentaProductos.entities.ShippingDetail;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ShippingDetailRepositoryTest extends AbstractDBTest {

    ShippingDetailRepository shippingDetailRepository;
    OrderRepository orderRepository;
    @Autowired
    public ShippingDetailRepositoryTest(ShippingDetailRepository shippingDetailRepository, OrderRepository orderRepository) {
        this.shippingDetailRepository = shippingDetailRepository;
        this.orderRepository = orderRepository;
    }
    ShippingDetail shippingDetail;
    Order order;
    @BeforeEach
    void setUp() {
        shippingDetail = ShippingDetail.builder()
                .id(1L)
                .address("street six")
                .conveyor("box travel")
                .numberGuide(1004)
                .build();

        order = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.now())
                .status(Status.ENTREGADO)
                .shippingDetail(shippingDetail)
                .build();
        shippingDetail.setOrder(order);
        shippingDetailRepository.save(shippingDetail);
        orderRepository.save(order);
    }

    @Test
    void givenShippingDetail_whenSave_thenReturnShippingDetail(){
        ShippingDetail shippingDetail = ShippingDetail.builder()
                .id(2L)
                .address("street six")
                .conveyor("box travel")
                .numberGuide(1004)
                .build();

        ShippingDetail shippingDetailSave = shippingDetailRepository.save(shippingDetail);

        assertThat(shippingDetailSave).isNotNull();
        assertThat(shippingDetailSave.getId()).isEqualTo(2L);
    }
    @Test
    void givenShippingDetail_whenFindById_thenReturnShippingDetailFound(){
        Long shippingDetailId = 1L;
        Optional<ShippingDetail> shippingDetailFind = shippingDetailRepository.findById(shippingDetailId);

        assertThat(shippingDetailFind).isPresent();
        assertThat(shippingDetailFind.get().getId()).isEqualTo(shippingDetailId);
        assertThat(shippingDetailFind.get().getConveyor()).isEqualTo("box travel");
    }
    @Test
    void givenShippingDetail_whenFindByOrderId_thenReturnShippingDetailFound(){
        Long orderId = 1L;

        ShippingDetail shippingDetailFound = shippingDetailRepository.findByOrderId(orderId);

        assertThat(shippingDetailFound).isNotNull();
        assertThat(shippingDetailFound.getId()).isEqualTo(1L);
    }

    @Test
    void givenShippingDetail_whenFindByConveyor_thenReturnShippingDetailFound(){
        String shippingDetailConveyor = "box travel";

        ShippingDetail shippingDetailFound = shippingDetailRepository.findByConveyor(shippingDetailConveyor);

        assertThat(shippingDetailFound).isNotNull();
        assertThat(shippingDetailFound.getId()).isEqualTo(1L);
    }
    @Test
    void givenShippingDetail_whenFindByOrderStatus_thenReturnShippingDetailFound(){
        Status shippingDetailStatus = Status.ENTREGADO;

        ShippingDetail shippingDetailFound = shippingDetailRepository.findByOrderStatus(shippingDetailStatus);

        assertThat(shippingDetailFound).isNotNull();
        assertThat(shippingDetailFound.getId()).isEqualTo(1L);
    }

    @Test
    void givenShippingDetail_whenDeleteById(){
        Long shippingDetailId = 1L;
        shippingDetailRepository.deleteById(shippingDetailId);

        Optional<ShippingDetail> shippingDetailVerify = shippingDetailRepository.findById(shippingDetailId);

        assertThat(shippingDetailVerify).isNotPresent();
    }
}