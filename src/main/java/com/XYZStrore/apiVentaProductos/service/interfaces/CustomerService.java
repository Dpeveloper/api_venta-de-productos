package com.XYZStrore.apiVentaProductos.service.interfaces;


import com.XYZStrore.apiVentaProductos.dto.CustomerDto;
import com.XYZStrore.apiVentaProductos.dto.CustomerSaveDto;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerSaveDto customerSaveDto);
    CustomerDto findById(Long id);
    CustomerDto updateCustomer(Long id, CustomerSaveDto customerSaveDto);
    CustomerDto deleteCustomerById(Long id);
    List<CustomerDto> findAllCustomer();
    CustomerDto findByEmail(String email);
    List<CustomerDto> findByAddress(String address);
    List<CustomerDto> findByNameStartingWith(String name);
}
