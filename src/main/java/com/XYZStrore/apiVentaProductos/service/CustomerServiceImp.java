package com.XYZStrore.apiVentaProductos.service;

import com.XYZStrore.apiVentaProductos.dto.CustomerDto;
import com.XYZStrore.apiVentaProductos.dto.CustomerSaveDto;
import com.XYZStrore.apiVentaProductos.entities.Customer;
import com.XYZStrore.apiVentaProductos.exception.CustomerNotFoundException;
import com.XYZStrore.apiVentaProductos.mapper.CustomerMapper;
import com.XYZStrore.apiVentaProductos.repository.CustomerRepository;
import com.XYZStrore.apiVentaProductos.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerServiceImp(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto saveCustomer(CustomerSaveDto customerSaveDto) {
        Customer customer = customerMapper.CustomerSaveDtoToCustomer(customerSaveDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerSaveDto customerSaveDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            customer.setName(customerSaveDto.name());
            customer.setEmail(customerSaveDto.email());
            customer.setAddress(customerSaveDto.address());

            Customer updatedCustomer = customerRepository.save(customer);
            return customerMapper.customerToCustomerDto(updatedCustomer);
        } else {
            throw new CustomerNotFoundException("No se encontró al cliente con el ID: " + id);
        }
    }
    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> findAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(CustomerNotFoundException::new);
        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> findByAddress(String address) {
        List<Customer> customers = customerRepository.findByAddress(address);
        return customers.stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> findByNameStartingWith(String name) {
        List<Customer> customers = customerRepository.findByNameStartingWith(name);
        return customers.stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }
}
