package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.CustomerResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.OrderResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customers")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "email_id")
    private Email email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders;


    public CustomerResponse toResponse(){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        if (orders != null){
            orderResponseList = orders.stream().map(Order::toResponse).toList();
        }
        return new CustomerResponse(customerId, customerName, address, phoneNumber, email.toResponse(), orderResponseList);
    }
}
