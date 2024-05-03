package com.XYZStrore.apiVentaProductos.mapper;

import com.XYZStrore.apiVentaProductos.dto.CustomerDto;
import com.XYZStrore.apiVentaProductos.dto.CustomerSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T01:30:53-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer customerSaveDtoToCustomer(CustomerSaveDto customerSaveDto) {
        if ( customerSaveDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.name( customerSaveDto.name() );
        customer.email( customerSaveDto.email() );
        customer.address( customerSaveDto.address() );

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
