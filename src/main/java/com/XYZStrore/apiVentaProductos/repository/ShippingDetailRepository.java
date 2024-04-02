package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.entities.ShippingDetail;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingDetailRepository extends JpaRepository<ShippingDetail,Long> {

    ShippingDetail findByOrderId(Long id);

    ShippingDetail findByConveyor(String conveyor);

    ShippingDetail findByOrderStatus(Status status);
}
