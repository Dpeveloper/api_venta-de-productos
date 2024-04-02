package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.CustomerDto;
import com.XYZStrore.apiVentaProductos.dto.CustomerSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer CustomerSaveDtoToCustomer(CustomerSaveDto customerSaveDto);
    CustomerDto customerToCustomerDto(Customer customer);
}
