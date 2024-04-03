package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ItemOrderDto;
import com.XYZStrore.apiVentaProductos.dto.ItemOrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ItemOrder;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T16:59:56-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class ItemOrderMapperImpl implements ItemOrderMapper {

    @Override
    public ItemOrder itemOrderSaveDtoToItemOrder(ItemOrderSaveDto itemOrderSaveDto) {
        if ( itemOrderSaveDto == null ) {
            return null;
        }

        ItemOrder itemOrder = new ItemOrder();

        return itemOrder;
    }

    @Override
    public ItemOrderDto itemOrderToItemOrderDto(ItemOrder itemOrder) {
        if ( itemOrder == null ) {
            return null;
        }

        Long id = null;
        Integer quantity = null;
        BigDecimal unitPrice = null;

        ItemOrderDto itemOrderDto = new ItemOrderDto( id, quantity, unitPrice );

        return itemOrderDto;
    }
}
