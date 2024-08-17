package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByCustomer_CustomerId(Long customerId);
}
