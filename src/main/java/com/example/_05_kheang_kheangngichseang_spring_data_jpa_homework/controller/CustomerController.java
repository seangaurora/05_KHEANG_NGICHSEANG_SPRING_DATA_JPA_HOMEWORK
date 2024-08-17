package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.controller;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.CustomerRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ApiResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return ResponseEntity.ok(ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new customer is inserted successfully!")
                .payload(customerResponse)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "customerId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
////        List<Customer> customers = customerService.getAllCustomers();
////        List<CustomerResponse> customerResponses = new ArrayList<>();
////        for (Customer customer : customers) {
////            customerResponses.add(customer.toResponse());
////        }
//
//        List<CustomerResponse> customerResponses = customerService.getAllCustomers().stream().map(Customer::toResponse).toList();
        List<CustomerResponse> customerResponseList = customerService.getAllCustomers(pageNo, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(ApiResponse.<List<CustomerResponse>>builder()
                .status(HttpStatus.OK)
                .message("All customers are fetched successfully!")
                .payload(customerResponseList)
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return ResponseEntity.ok(ApiResponse.<CustomerResponse>builder()
                        .status(HttpStatus.OK)
                        .message("The customer with ID " + id + " is fetched successfully!")
                        .payload(customerResponse)
                        .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomerById(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCustomerById(id, customerRequest);
        return ResponseEntity.ok(ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("The customer with ID " + id + " is deleted successfully!")
                .payload(customerResponse)
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok(ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("The customer with ID " + id + " is deleted successfully!")
                .build());
    }

}
