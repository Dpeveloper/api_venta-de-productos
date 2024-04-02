package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.ProductDto;
import com.XYZStrore.apiVentaProductos.dto.ProductSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Product;
import com.XYZStrore.apiVentaProductos.exception.ProductNotFoundException;
import com.XYZStrore.apiVentaProductos.mapper.ProductMapper;
import com.XYZStrore.apiVentaProductos.repository.ProductRepository;
import com.XYZStrore.apiVentaProductos.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImp(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto saveProduct(ProductSaveDto productSaveDto) {
        Product product = productMapper.productSaveDtoToProduct(productSaveDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDto(savedProduct);
    }

    @Override
    public ProductDto findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.productToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductSaveDto productSaveDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setName(productSaveDto.name());
            product.setPrice(productSaveDto.price());
            product.setStock(productSaveDto.stock());

            Product updatedProduct = productRepository.save(product);
            return productMapper.productToProductDto(updatedProduct);
        } else {
            throw new ProductNotFoundException("No se encontró el producto con el ID: " + id);
        }
    }


    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findByName(String name) {
        Product product = productRepository.findByName(name);
        return productMapper.productToProductDto(product);
    }

    @Override
    public List<ProductDto> findByStockGreaterThan(int stock) {
        List<Product> products = productRepository.findByStockGreaterThan(stock);
        return products.stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByPriceLessThanAndStockLessThan(BigDecimal price, Integer stock) {
        List<Product> products = productRepository.findByPriceLessThanAndStockLessThan(price, stock);
        return products.stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }
}
