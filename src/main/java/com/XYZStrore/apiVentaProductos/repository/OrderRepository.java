package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.Entities.Customer;
import com.XYZStrore.apiVentaProductos.Entities.Order;
import com.XYZStrore.apiVentaProductos.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByOrderDateBetween(LocalDateTime orderDate, LocalDateTime orderDate2);
    List<Order> findByCustomerAndStatus(Customer customer, Status status);
    @Query("SELECT o FROM orders o JOIN FETCH o.itemOrder WHERE o.customer = :customer")
    List<Order> findByCustomerWithOrderItems(@Param("customer") Customer customer);

}
