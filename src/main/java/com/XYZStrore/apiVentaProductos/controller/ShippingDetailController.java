package com.XYZStrore.apiVentaProductos.controller;

import com.XYZStrore.apiVentaProductos.dto.ShippingDetailDto;
import com.XYZStrore.apiVentaProductos.dto.ShippingDetailSaveDto;
import com.XYZStrore.apiVentaProductos.service.interfaces.ShippingDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
public class ShippingDetailController {
    private final ShippingDetailService shippingDetailService;

    public ShippingDetailController(ShippingDetailService shippingDetailService) {
        this.shippingDetailService = shippingDetailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingDetailDto> getShippingDetailById(@PathVariable Long id) {
        return ResponseEntity.ok().body(shippingDetailService.findShippingDetailById(id));
    }

    @GetMapping
    public ResponseEntity<List<ShippingDetailDto>> getAllShippingDetails() {
        return ResponseEntity.ok().body(shippingDetailService.findAllShippingDetails());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ShippingDetailDto> getShippingDetailByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(shippingDetailService.findByOrderId(orderId));
    }

    @GetMapping("/carrier")
    public ResponseEntity<ShippingDetailDto> getShippingDetailByCarrier(@RequestParam String name) {
        return ResponseEntity.ok().body(shippingDetailService.findByConveyor(name));
    }

    @PostMapping
    public ResponseEntity<ShippingDetailDto> createShippingDetail(@RequestBody ShippingDetailSaveDto shippingDetailSaveDto) {
        return ResponseEntity.ok().body(shippingDetailService.saveShippingDetail(shippingDetailSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingDetailDto> updateShippingDetail(@PathVariable Long id, @RequestBody ShippingDetailSaveDto shippingDetailSaveDto) {
        return ResponseEntity.ok().body(shippingDetailService.updateShippingDetail(id, shippingDetailSaveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShippingDetail(@PathVariable Long id) {
        shippingDetailService.deleteShippingDetailById(id);
        return ResponseEntity.noContent().build();
    }
}


