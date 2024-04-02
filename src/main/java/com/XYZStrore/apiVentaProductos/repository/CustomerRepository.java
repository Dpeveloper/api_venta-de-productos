package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByAddress(String address);
    List<Customer> findByNameStartingWith(String name);
}
