package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private double unitPrice;
    private String description;

    public Product toEntity(){
        return new Product(null, this.productName, this.unitPrice, this.description, null);
    }
    public Product toEntity(Long id){
        return new Product(id, this.productName, this.unitPrice, this.description, null);
    }
}
