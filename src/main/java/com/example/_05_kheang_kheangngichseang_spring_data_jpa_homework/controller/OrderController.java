package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.controller;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.OrderStatusEnum;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ApiResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.OrderRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.OrderResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("{customerId}")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrderOfACustomer(
            @PathVariable Long customerId,
            @RequestBody List<OrderRequest> orderRequests) {
        OrderResponse orderResponse = orderService.createOrder(customerId, orderRequests);
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new product is inserted successfully!")
                .payload(orderResponse)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable long id) {
        OrderResponse orderResponse = orderService.getOrderById(id);
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("The product with ID " + id + " is fetched successfully!")
                .payload(orderResponse)
                .build());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<OrderResponse> orderList = orderService.getOrderByCustomerId(customerId);
        return ResponseEntity.ok(ApiResponse.<List<OrderResponse>>builder()
                .status(HttpStatus.OK)
                .message("All orders of customer with ID " + customerId + " are fetched successfully!")
                .payload(orderList)
                .build());
    }

    @PutMapping
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrderById(@RequestParam Long id, @RequestParam OrderStatusEnum status) {
         OrderResponse updatedOrder = orderService.updateOrderStatusById(id, status);
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("The order with ID " + id + " is updated successfully!")
                .payload(updatedOrder)
                .build());
    }

}
