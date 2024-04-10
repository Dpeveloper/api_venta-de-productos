package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ShippingDetailDto;
import com.XYZStrore.apiVentaProductos.dto.ShippingDetailSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ShippingDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T11:19:09-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class ShippingDetailMapperImpl implements ShippingDetailMapper {

    @Override
    public ShippingDetail shippingDetailSaveDtoToShippingDetail(ShippingDetailSaveDto shippingDetailSaveDto) {
        if ( shippingDetailSaveDto == null ) {
            return null;
        }

        ShippingDetail.ShippingDetailBuilder shippingDetail = ShippingDetail.builder();

        shippingDetail.address( shippingDetailSaveDto.address() );
        shippingDetail.conveyor( shippingDetailSaveDto.conveyor() );
        shippingDetail.numberGuide( shippingDetailSaveDto.numberGuide() );

        return shippingDetail.build();
    }

    @Override
    public ShippingDetailDto shippingDetailToShippingDetailDto(ShippingDetail shippingDetail) {
        if ( shippingDetail == null ) {
            return null;
        }

        Long id = null;
        String conveyor = null;
        Integer numberGuide = null;

        id = shippingDetail.getId();
        conveyor = shippingDetail.getConveyor();
        numberGuide = shippingDetail.getNumberGuide();

        String addres = null;

        ShippingDetailDto shippingDetailDto = new ShippingDetailDto( id, addres, conveyor, numberGuide );

        return shippingDetailDto;
    }
}
