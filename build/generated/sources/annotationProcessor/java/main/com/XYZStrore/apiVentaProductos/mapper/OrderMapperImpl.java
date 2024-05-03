package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.OrderDto;
import com.XYZStrore.apiVentaProductos.dto.OrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Order;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T01:30:53-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderSaveDtoToOrder(OrderSaveDto orderSaveDto) {
        if ( orderSaveDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.orderDate( orderSaveDto.orderDate() );
        order.status( orderSaveDto.status() );

        return order.build();
    }

    @Override
    public OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        LocalDateTime orderDate = null;
        Status status = null;

        id = order.getId();
        orderDate = order.getOrderDate();
        status = order.getStatus();

        OrderDto orderDto = new OrderDto( id, orderDate, status );

        return orderDto;
    }
}
