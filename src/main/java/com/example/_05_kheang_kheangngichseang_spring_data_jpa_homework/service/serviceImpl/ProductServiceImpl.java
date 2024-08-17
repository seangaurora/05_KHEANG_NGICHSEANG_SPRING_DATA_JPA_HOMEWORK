package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.serviceImpl;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Product;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request.ProductRequest;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.ProductResponse;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.repository.ProductRepository;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity()).toResponse();
    }

    @Override
    public List<ProductResponse> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        return productRepository.findAll(pageable).getContent().stream().map(Product::toResponse).toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity(id)).toResponse();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
