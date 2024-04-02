package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);
    List<Customer> findByAddress(String address);
    List<Customer> findByNameStartingWith(String name);
}
