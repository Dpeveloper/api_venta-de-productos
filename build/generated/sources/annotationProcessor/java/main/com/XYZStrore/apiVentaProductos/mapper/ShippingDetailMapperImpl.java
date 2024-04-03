package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ShippingDetailDto;
import com.XYZStrore.apiVentaProductos.dto.ShippingDetailSaveDto;
import com.XYZStrore.apiVentaProductos.entities.ShippingDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T16:59:56-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class ShippingDetailMapperImpl implements ShippingDetailMapper {

    @Override
    public ShippingDetail shippingDetailSaveDtoToShippingDetail(ShippingDetailSaveDto shippingDetailSaveDto) {
        if ( shippingDetailSaveDto == null ) {
            return null;
        }

        ShippingDetail shippingDetail = new ShippingDetail();

        return shippingDetail;
    }

    @Override
    public ShippingDetailDto shippingDetailToShippingDetailDto(ShippingDetail shippingDetail) {
        if ( shippingDetail == null ) {
            return null;
        }

        Long id = null;
        String addres = null;
        String conveyor = null;
        Integer numberGuide = null;

        ShippingDetailDto shippingDetailDto = new ShippingDetailDto( id, addres, conveyor, numberGuide );

        return shippingDetailDto;
    }
}
