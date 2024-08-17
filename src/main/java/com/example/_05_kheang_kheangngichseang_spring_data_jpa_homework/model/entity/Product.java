package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private double price;

    private String description;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<ProductOrder> orders;


    public ProductResponse toResponse() {
        return new ProductResponse(this.productId, this.productName, this.price, this.description);
    }
}
