package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.ProductDto;
import com.XYZStrore.apiVentaProductos.dto.ProductSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Product;
import com.XYZStrore.apiVentaProductos.mapper.ProductMapper;
import com.XYZStrore.apiVentaProductos.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImp productService;

    Product product;
    ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Product Name");
        product.setPrice(new BigDecimal("100.00"));
        product.setStock(10);

        productDto = new ProductDto(1L, "Product Name", new BigDecimal("100.00"), 10);
    }

    @Test
    void givenProductService_whenSaveProduct_thenReturnSavedProduct() {
        when(productRepository.save(any())).thenReturn(product);

        ProductSaveDto productToSave = new ProductSaveDto("Product Name", new BigDecimal("100.00"), 10);

        when(productMapper.productToProductDto(any())).thenReturn(productDto);

        ProductDto savedProduct = productService.saveProduct(productToSave);

        assertThat(savedProduct).isNotNull();
    }

    @Test
    void givenProductService_whenFindProductById_thenReturnFoundProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        when(productMapper.productToProductDto(any())).thenReturn(productDto);

        ProductDto foundProduct = productService.findProductById(1L);

        assertThat(foundProduct).isNotNull();
    }

    @Test
    void givenProductService_whenUpdateProduct_thenReturnUpdatedProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        ProductSaveDto productToUpdate = new ProductSaveDto("Updated Product Name", new BigDecimal("200.00"), 20);

        when(productRepository.save(any())).thenReturn(product);

        when(productMapper.productToProductDto(any())).thenReturn(productDto);

        ProductDto updatedProduct = productService.updateProduct(1L, productToUpdate);

        assertThat(updatedProduct).isNotNull();
    }

    @Test
    void givenProductService_whenDeleteProduct_thenVoid() {
        Long productId = 1L;
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        productService.deleteProductById(productId);

        verify(productRepository, times(1)).deleteById(any());
    }

    @Test
    void givenProductService_whenGetAllProducts_thenReturnListOfProduct() {
        List<Product> products = List.of(product);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> productDtos = productService.findAllProducts();

        assertThat(productDtos).isNotEmpty();
        assertThat(productDtos).hasSize(1);
    }
}


