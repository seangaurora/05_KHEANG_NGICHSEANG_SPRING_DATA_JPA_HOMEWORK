package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.controller;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.ProductRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ApiResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ProductResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addProduct(productRequest);
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new product is inserted successfully!")
                .payload(productResponse)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
            ) {
        List<ProductResponse> productResponseList = productService.getAllProducts(pageNo, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(ApiResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.CREATED)
                .message("All products are fetched successfully!")
                .payload(productResponseList)
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED)
                .message("The product with ID " + id + " is fetched successfully!")
                .payload(productResponse)
                .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductById(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProductById(id, productRequest);
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("The product with ID " + id + " is update successfully!")
                .payload(productResponse)
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("The product with ID " + id + " is deleted successfully!")
                .build());
    }

}
