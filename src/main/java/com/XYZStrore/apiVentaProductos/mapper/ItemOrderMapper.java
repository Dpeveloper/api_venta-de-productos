package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ItemOrderDto;
import com.XYZStrore.apiVentaProductos.dto.ItemOrderSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ItemOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemOrderMapper {
    ItemOrder itemOrderSaveDtoToItemOrder(ItemOrderSaveDto itemOrderSaveDto);
    ItemOrderDto itemOrderToItemOrderDto(ItemOrder itemOrder);
}
