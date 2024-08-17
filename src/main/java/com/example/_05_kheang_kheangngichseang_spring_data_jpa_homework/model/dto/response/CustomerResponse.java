package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    private Long id;
    private String customerName;
    private String address;
    private String phoneNumber;
    private EmailResponse emailResponse;
    private List<OrderResponse> orderList;
}
