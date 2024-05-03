package com.XYZStrore.apiVentaProductos.service.interfaces;

import com.XYZStrore.apiVentaProductos.dto.ShippingDetailDto;
import com.XYZStrore.apiVentaProductos.dto.ShippingDetailSaveDto;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;

import java.util.List;

public interface ShippingDetailService {
    ShippingDetailDto saveShippingDetail(ShippingDetailSaveDto shippingDetailSaveDto);
    ShippingDetailDto findShippingDetailById(Long id);
    ShippingDetailDto updateShippingDetail(Long id, ShippingDetailSaveDto shippingDetailSaveDto);
    void deleteShippingDetailById(Long id);
    List<ShippingDetailDto> findAllShippingDetails();
    ShippingDetailDto findByOrderId(Long orderId);
    ShippingDetailDto findByConveyor(String conveyor);
    ShippingDetailDto findByOrderStatus(Status status);
}
