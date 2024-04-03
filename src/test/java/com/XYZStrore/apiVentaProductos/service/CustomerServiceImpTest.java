package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.CustomerDto;
import com.XYZStrore.apiVentaProductos.dto.CustomerSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import com.XYZStrore.apiVentaProductos.mapper.CustomerMapper;
import com.XYZStrore.apiVentaProductos.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CustomerServiceImpTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImp customerService;

    Customer customer, customer2, customer3;
    CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Daniel Pinilla");
        customer.setEmail("@test");
        customer.setAddress("street 6");

        customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Cristian Zuñiga");
        customer2.setEmail("@test");
        customer2.setAddress("street 7");

        customer3 = new Customer();
        customer3.setId(3L);
        customer3.setName("maxima nota");
        customer3.setEmail("@test");
        customer3.setAddress("street 8");

        customerDto = new CustomerDto(1L,"Daniel Pinilla", "@test", "street 6");
    }

    @Test
    void givenCustomerService_whenSaveCustomer_thenReturnSavedCustomer() {
        when(customerRepository.save(any())).thenReturn(customer);

        CustomerSaveDto customerToSave = new CustomerSaveDto("Daniel Pinilla", "@test", "street 6");

        when(customerMapper.customerToCustomerDto(any())).thenReturn(customerDto);

        CustomerDto savedCustomer = customerService.saveCustomer(customerToSave);

        assertThat(savedCustomer).isNotNull();
    }

    @Test
    void givenCustomerService_whenUpdateCustomer_thenReturnUpdatedCustomer() {
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));

        CustomerSaveDto customerToUpdate = new CustomerSaveDto("Daniel Pinilla", "@test", "street 6");

        when(customerRepository.save(any())).thenReturn(customer);

        when(customerMapper.customerToCustomerDto(any())).thenReturn(customerDto);

        CustomerDto updatedCustomer = customerService.updateCustomer(1L, customerToUpdate);

        assertThat(updatedCustomer).isNotNull();
    }

    @Test
    void givenCustomerService_whenFindCustomerById_thenReturnFoundCustomer() {
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));

        when(customerMapper.customerToCustomerDto(any())).thenReturn(customerDto);

        CustomerDto foundCustomer = customerService.findById(1L);

        assertThat(foundCustomer).isNotNull();
    }

    @Test
    void givenCustomerService_whenRemoveCustomer_thenVoid() {
        Long customerId = 1L;
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        customerService.deleteCustomerById(customerId);

        verify(customerRepository, times(1)).deleteById(any());
    }

    @Test
    void givenCustomerService_whenGetAllCustomer_thenReturnListOfCustomer() {
        List<Customer> customers = List.of(customer, customer2, customer3);

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDto> customerDtos = customerService.findAllCustomer();

        assertThat(customerDtos).isNotEmpty();
        assertThat(customerDtos).hasSize(3);
    }

    @Test
    void givenCustomerService_whenFindCustomerByEmail_thenReturnFoundCustomer() {
        String customerEmail = "@test";

        when(customerRepository.findByEmail(any())).thenReturn(Optional.of(customer));

        when(customerMapper.customerToCustomerDto(any())).thenReturn(customerDto);

        CustomerDto customerDto = customerService.findByEmail(customerEmail);

        assertThat(customerDto).isNotNull();
    }

    @Test
    void givenCustomerService_whenFindByAddress_thenReturnListOfCustomer() {
        List<Customer> customers = List.of(customer, customer3);
        String customerAddress = "street six";

        when(customerRepository.findByAddress(any())).thenReturn(customers);

        List<CustomerDto> customerDtos = customerService.findByAddress(customerAddress);

        assertThat(customerDtos).isNotEmpty();
        assertThat(customerDtos).hasSize(2);
    }

    @Test
    void givenCustomerService_whenFindByNameStartingWith_thenReturnListOfCustomer() {
        List<Customer> customers = List.of(customer, customer3);
        String customerName = "Daniel";

        when(customerRepository.findByNameStartingWith(any())).thenReturn(customers);

        List<CustomerDto> customerDtos = customerService.findByNameStartingWith(customerName);

        assertThat(customerDtos).isNotEmpty();
        assertThat(customerDtos).hasSize(2);
    }

}



