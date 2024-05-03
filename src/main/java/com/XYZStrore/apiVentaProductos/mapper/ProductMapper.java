package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.ProductDto;
import com.XYZStrore.apiVentaProductos.dto.ProductSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product productSaveDtoToProduct(ProductSaveDto productSaveDto);
    ProductDto productToProductDto(Product product);
}
