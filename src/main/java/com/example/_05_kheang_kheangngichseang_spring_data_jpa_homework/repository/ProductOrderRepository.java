package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
