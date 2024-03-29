package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.Entities.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder,Long> {

    List<ItemOrder> findByOrderId(Long id);
    List<ItemOrder> findByProductId(Long id);
    @Query("SELECT SUM(io.quantity) * io.unitPrice FROM item_orders io WHERE io.product.id = :productId")
    BigDecimal getTotalSalesAmountForProduct(@Param("productId") Long productId);

}
