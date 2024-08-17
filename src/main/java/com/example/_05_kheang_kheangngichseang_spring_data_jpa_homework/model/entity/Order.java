package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.OrderStatusEnum;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.OrderResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProductOrder> products;

    public OrderResponse toResponse(){
        //convert from productOrder to ProductResponse type of products
        List<ProductResponse> productResponseList = new ArrayList<>();
        if (products != null) {
            for(ProductOrder productOrder : products){
            Product product = productOrder.getProduct();
            ProductResponse productResponse = product.toResponse();
                productResponseList.add(productResponse);
            }
        }
                                                                                                //ProductResponse
        return new OrderResponse(this.orderId, this.orderDate, this.totalAmount, this.status, productResponseList);
    }
}
