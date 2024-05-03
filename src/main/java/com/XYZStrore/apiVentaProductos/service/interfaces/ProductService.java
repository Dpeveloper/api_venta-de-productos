package com.XYZStrore.apiVentaProductos.service.interfaces;

import com.XYZStrore.apiVentaProductos.dto.ProductDto;
import com.XYZStrore.apiVentaProductos.dto.ProductSaveDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductSaveDto productSaveDto);
    ProductDto findProductById(Long id);
    ProductDto updateProduct(Long id, ProductSaveDto productSaveDto);
    void deleteProductById(Long id);
    List<ProductDto> findAllProducts();
    ProductDto findByName(String name);
    List<ProductDto> findByStockGreaterThan(int stock);
    List<ProductDto> findByPriceLessThanAndStockLessThan(BigDecimal price, Integer stock);
}


