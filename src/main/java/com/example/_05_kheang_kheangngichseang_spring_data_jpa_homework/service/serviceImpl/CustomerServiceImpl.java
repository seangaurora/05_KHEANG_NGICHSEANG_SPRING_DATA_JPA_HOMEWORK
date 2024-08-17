package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.serviceImpl;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Customer;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.CustomerRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository.CustomerRepository;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity()).toResponse();
    }

    @Override
    public List<CustomerResponse> getAllCustomers(int pageNo, int pageSize, String sortBy, String sortDirection) {
//        Boolean chk = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name());
//        Sort sort = null;
//        if (chk) {
//            sort = Sort.by(sortBy).ascending();
//        }
//        else{
//            sort = Sort.by(sortBy).descending();
//        }

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        return customerRepository.findAll(pageable).getContent().stream().map(Customer::toResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
//        Optional<Customer> customer = customerRepository.findById(id);
//        if(customer.isPresent()) {
//            return customer.get().toResponse();
//        }
//        return null;
        return customerRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public CustomerResponse updateCustomerById(Long id, CustomerRequest customerRequest) {

        return customerRepository.save(customerRequest.toEntity(id)).toResponse();
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
