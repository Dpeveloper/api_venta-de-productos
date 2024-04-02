package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    List<Product> findByStockGreaterThan(int stock);
    List<Product> findByPriceLessThanAndStockLessThan(BigDecimal price, Integer stock);
}
