package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ItemOrderDto;
import com.XYZStrore.apiVentaProductos.dto.ItemOrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ItemOrder;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T11:19:09-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class ItemOrderMapperImpl implements ItemOrderMapper {

    @Override
    public ItemOrder itemOrderSaveDtoToItemOrder(ItemOrderSaveDto itemOrderSaveDto) {
        if ( itemOrderSaveDto == null ) {
            return null;
        }

        ItemOrder.ItemOrderBuilder itemOrder = ItemOrder.builder();

        itemOrder.id( itemOrderSaveDto.id() );
        itemOrder.quantity( itemOrderSaveDto.quantity() );
        itemOrder.unitPrice( itemOrderSaveDto.unitPrice() );

        return itemOrder.build();
    }

    @Override
    public ItemOrderDto itemOrderToItemOrderDto(ItemOrder itemOrder) {
        if ( itemOrder == null ) {
            return null;
        }

        Long id = null;
        Integer quantity = null;
        BigDecimal unitPrice = null;

        id = itemOrder.getId();
        quantity = itemOrder.getQuantity();
        unitPrice = itemOrder.getUnitPrice();

        ItemOrderDto itemOrderDto = new ItemOrderDto( id, quantity, unitPrice );

        return itemOrderDto;
    }
}
