package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.OrderStatusEnum;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.OrderRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long customerId, List<OrderRequest> orderRequests);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getOrderByCustomerId(Long customerId);

    OrderResponse updateOrderStatusById(Long id, OrderStatusEnum status);
}
