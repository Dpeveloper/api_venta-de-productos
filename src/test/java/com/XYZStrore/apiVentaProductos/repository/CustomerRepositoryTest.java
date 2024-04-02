package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.AbstractDBTest;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerRepositoryTest extends AbstractDBTest {

    CustomerRepository customerRepository;
    @Autowired
    public CustomerRepositoryTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    Customer customer;
    Customer customer2;
    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(1L)
                .name("test")
                .email("@test")
                .address("street six")
                .build();

        customer2 = Customer.builder()
                .id(3L)
                .name("willson")
                .email("@test")
                .address("street six")
                .build();

        customerRepository.save(customer);
        customerRepository.save(customer2);
    }

    @Test
    void givenCustomer_whenSave_thenReturnCustomer(){
        Customer customer = Customer.builder()
                .id(2L)
                .name("test")
                .email("@test")
                .address("street six")
                .build();

        Customer customerSave = customerRepository.save(customer);

        assertThat(customerSave).isNotNull();
        assertThat(customerSave.getId()).isEqualTo(2L);
    }
    @Test
    void givenCustomer_whenFindById_thenReturnCustomerFound(){
        Long customerId = 1L;
        Optional<Customer> customerFind = customerRepository.findById(customerId);

        assertThat(customerFind).isPresent();
        assertThat(customerFind.get().getId()).isEqualTo(customerId);
        assertThat(customerFind.get().getName()).isEqualTo("test");
    }

    @Test
    void givenCustomer_whenFindByEmail_thenReturnCustomerFound(){
        String customerEmail = "@test";
        Optional<Customer> customerFind = customerRepository.findByEmail(customerEmail);

        assertThat(customerFind).isPresent();
        assertThat(customerFind.get().getEmail()).isEqualTo(customerEmail);
    }

    @Test
    void givenCustomer_whenFindByAddres_thenReturnCustomerFound(){
        String customerAddres = "street six";
        List<Customer> customersFind = customerRepository.findByAddress(customerAddres);

        assertThat(customersFind).isNotNull();
        assertThat(customersFind).hasSize(2);
        assertThat(customersFind.get(0).getAddress()).isEqualTo(customerAddres);
    }

    @Test
    void givenCustomer_whenFindByStratingWith_thenReturnCustomerFound(){
        String customerStarting = "willson";
        List<Customer> customersFind = customerRepository.findByNameStartingWith(customerStarting);

        assertThat(customersFind).isNotNull();
        assertThat(customersFind).hasSize(1);
        assertThat(customersFind.get(0).getName()).isEqualTo(customerStarting);
    }

    @Test
    void givenCustomer_whenDeleteById(){
        Long customerId = 1L;
        customerRepository.deleteById(customerId);

        Optional<Customer> customerVerify = customerRepository.findById(customerId);

        assertThat(customerVerify).isNotPresent();
    }
}