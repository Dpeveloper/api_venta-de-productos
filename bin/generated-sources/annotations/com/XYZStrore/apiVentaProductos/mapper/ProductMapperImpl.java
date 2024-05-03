package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ProductDto;
import com.XYZStrore.apiVentaProductos.dto.ProductSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Product;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T11:19:10-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productSaveDtoToProduct(ProductSaveDto productSaveDto) {
        if ( productSaveDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productSaveDto.name() );
        product.price( productSaveDto.price() );
        product.stock( productSaveDto.stock() );

        return product.build();
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal price = null;
        Integer stock = null;

        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        stock = product.getStock();

        ProductDto productDto = new ProductDto( id, name, price, stock );

        return productDto;
    }
}
