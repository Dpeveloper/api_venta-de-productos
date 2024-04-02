package com.XYZStrore.apiVentaProductos.controller;

import com.XYZStrore.apiVentaProductos.dto.ProductDto;
import com.XYZStrore.apiVentaProductos.dto.ProductSaveDto;
import com.XYZStrore.apiVentaProductos.service.ProductServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceImp productService;

    public ProductController(ProductServiceImp productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ProductDto> getProductByName(@RequestParam String searchTerm) {
        return ResponseEntity.ok(productService.findByName(searchTerm));
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductDto>> getProductsInStock() {
        return ResponseEntity.ok(productService.findByStockGreaterThan(0));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductSaveDto productSaveDto) {
        return ResponseEntity.ok(productService.saveProduct(productSaveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductSaveDto productSaveDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productSaveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}

