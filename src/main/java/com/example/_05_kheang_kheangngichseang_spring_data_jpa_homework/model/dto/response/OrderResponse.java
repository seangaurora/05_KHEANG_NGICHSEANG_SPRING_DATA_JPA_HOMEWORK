package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.OrderStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private LocalDate orderDate;
    private double totalAmount;
//    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    private List<ProductResponse> products;

}


