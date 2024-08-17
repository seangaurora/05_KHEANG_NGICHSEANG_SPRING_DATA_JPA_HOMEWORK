package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
