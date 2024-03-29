package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.AbstractDBTest;
import com.XYZStrore.apiVentaProductos.Entities.Payment;
import com.XYZStrore.apiVentaProductos.Entities.Product;
import com.XYZStrore.apiVentaProductos.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest extends AbstractDBTest {

    ProductRepository productRepository;
    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("test")
                .price(new BigDecimal(10000))
                .stock(10)
                .build();

        productRepository.save(product);
    }

    @Test
    void givenProduct_whenSave_thenReturnProduct(){
        Product product = Product.builder()
                .id(2L)
                .name("test")
                .price(new BigDecimal(10000))
                .stock(10)
                .build();

        Product productSave = productRepository.save(product);

        assertThat(productSave).isNotNull();
        assertThat(productSave.getId()).isEqualTo(2L);
    }

    @Test
    void givenProduct_whenFindById_thenReturnProductFound(){
        Long productId = 1L;
        Optional<Product> productFind = productRepository.findById(productId);

        assertThat(productFind).isPresent();
        assertThat(productFind.get().getId()).isEqualTo(productId);
        assertThat(productFind.get().getStock()).isEqualTo(10);
    }

    @Test
    void givenProduct_whenFindByName_thenReturnProduct(){
        String name = "test";
        Product product = productRepository.findByName(name);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(1L);
    }

    @Test
    void givenProduct_whenFindByStockGreaterThan_thenReturnProduct(){
        List<Product> productList = productRepository.findByStockGreaterThan(0);
        assertThat(productList).hasSize(1);
        assertThat(productList.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void givenProduct_whenFindByPriceLessThanAndStockLessThan_thenReturnProduct(){
        BigDecimal price = new BigDecimal(100000);
        Integer stock = 15;

        List<Product> products = productRepository.findByPriceLessThanAndStockLessThan(price,stock);

        assertThat(products).hasSize(1);
        assertThat(products.get(0).getId()).isEqualTo(1L);
    }
    @Test
    void givenProduct_whenDeleteById(){
        Long productId = 1L;
        productRepository.deleteById(productId);

        Optional<Product> productDelete = productRepository.findById(productId);

        assertThat(productDelete).isNotPresent();
    }
}