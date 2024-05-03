package com.XYZStrore.apiVentaProductos.service.interfaces;

import com.XYZStrore.apiVentaProductos.dto.ItemOrderDto;
import com.XYZStrore.apiVentaProductos.dto.ItemOrderSaveDto;

import java.math.BigDecimal;
import java.util.List;

public interface ItemOrderService {
    ItemOrderDto saveItemOrder(ItemOrderSaveDto itemOrderSaveDto);
    ItemOrderDto findItemOrderById(Long id);
    ItemOrderDto updateItemOrder(Long id, ItemOrderSaveDto itemOrderSaveDto);
    void deleteItemOrder(Long id);
    List<ItemOrderDto> findByOrderId(Long orderId);
    List<ItemOrderDto> findByProductId(Long productId);
    BigDecimal getTotalSalesAmountForProduct(Long productId);
    List<ItemOrderDto> findAllItemOrders();
}
