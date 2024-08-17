package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.ProductRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDirection);

    ProductResponse getProductById(Long id);

    ProductResponse updateProductById(Long id, ProductRequest productRequest);

    void deleteProductById(Long id);
}
