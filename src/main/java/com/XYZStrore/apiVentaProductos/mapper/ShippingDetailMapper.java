package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ShippingDetailDto;
import com.XYZStrore.apiVentaProductos.dto.ShippingDetailSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ShippingDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShippingDetailMapper {

    ShippingDetail shippingDetailSaveDtoToShippingDetail(ShippingDetailSaveDto shippingDetailSaveDto);

    ShippingDetailDto shippingDetailToShippingDetailDto(ShippingDetail shippingDetail);
}
