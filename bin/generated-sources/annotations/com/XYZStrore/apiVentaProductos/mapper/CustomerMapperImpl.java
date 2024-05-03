package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.CustomerDto;
import com.XYZStrore.apiVentaProductos.dto.CustomerSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T11:19:09-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer CustomerSaveDtoToCustomer(CustomerSaveDto customerSaveDto) {
        if ( customerSaveDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.address( customerSaveDto.address() );
        customer.email( customerSaveDto.email() );
        customer.name( customerSaveDto.name() );

        return customer.build();
    }

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String address = null;

        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
        address = customer.getAddress();

        CustomerDto customerDto = new CustomerDto( id, name, email, address );

        return customerDto;
    }
}
