package com.XYZStrore.apiVentaProductos.repository;

import com.XYZStrore.apiVentaProductos.entities.Customer;
import com.XYZStrore.apiVentaProductos.entities.Order;
import com.XYZStrore.apiVentaProductos.enumdetail.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByOrderDateBetween(LocalDateTime orderDate, LocalDateTime orderDate2);
    List<Order> findByCustomerAndStatus(Customer customer, Status status);
    @Query("select o from orders o join fetch o.itemOrder where o.customer.id = :idCustomer")
    List<Order> findByCustomerWithOrderItems(@Param("idCustomer") Long idCustomer);

}
