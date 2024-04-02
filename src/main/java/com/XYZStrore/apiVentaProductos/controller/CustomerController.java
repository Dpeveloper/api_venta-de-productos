package com.XYZStrore.apiVentaProductos.controller;

import com.XYZStrore.apiVentaProductos.dto.CustomerDto;
import com.XYZStrore.apiVentaProductos.dto.CustomerSaveDto;
import com.XYZStrore.apiVentaProductos.service.interfaces.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerSaveDto customerSaveDto) {
        return ResponseEntity.ok().body(customerService.saveCustomer(customerSaveDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(customerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerSaveDto customerSaveDto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerSaveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.findByEmail(email));
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<CustomerDto>> getCustomersByAddress(@PathVariable String address) {
        return ResponseEntity.ok(customerService.findByAddress(address));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerDto>> getCustomersByNameStartingWith(@PathVariable String name) {
        return ResponseEntity.ok(customerService.findByNameStartingWith(name));
    }
}

