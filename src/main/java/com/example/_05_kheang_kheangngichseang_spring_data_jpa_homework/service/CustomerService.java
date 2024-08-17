package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.CustomerRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomers(int pageNo, int pageSize, String sortBy, String sortDirection);

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomerById(Long id, CustomerRequest customerRequest);

    void deleteCustomerById(Long id);


}
