package com.XYZStrore.apiVentaProductos.controller;

import com.XYZStrore.apiVentaProductos.dto.ItemOrderDto;
import com.XYZStrore.apiVentaProductos.dto.ItemOrderSaveDto;
import com.XYZStrore.apiVentaProductos.service.interfaces.ItemOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
public class ItemOrderController {
    private final ItemOrderService itemOrderService;

    public ItemOrderController(ItemOrderService itemOrderService) {
        this.itemOrderService = itemOrderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemOrderDto> getItemOrderById(@PathVariable Long id) {
        return ResponseEntity.ok().body(itemOrderService.findItemOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemOrderDto>> getAllItemOrders() {
        return ResponseEntity.ok().body(itemOrderService.findAllItemOrders());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ItemOrderDto>> getItemOrdersByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(itemOrderService.findByOrderId(orderId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ItemOrderDto>> getItemOrdersByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok().body(itemOrderService.findByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<ItemOrderDto> createItemOrder(@RequestBody ItemOrderSaveDto itemOrderSaveDto) {
        return ResponseEntity.ok().body(itemOrderService.saveItemOrder(itemOrderSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemOrderDto> updateItemOrder(@PathVariable Long id, @RequestBody ItemOrderSaveDto itemOrderSaveDto) {
        return ResponseEntity.ok().body(itemOrderService.updateItemOrder(id, itemOrderSaveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemOrder(@PathVariable Long id) {
        itemOrderService.deleteItemOrder(id);
        return ResponseEntity.noContent().build();
    }
}



