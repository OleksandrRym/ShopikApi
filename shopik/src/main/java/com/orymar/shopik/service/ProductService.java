package com.orymar.shopik.service;

import com.orymar.shopik.http.request.ProductCreateRequest;
import com.orymar.shopik.repo.ProductRepo;
import com.orymar.shopik.repo.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public ProductEntity create(ProductCreateRequest productCreateRequest){
        ProductEntity product = ProductEntity.builder()
                .name(productCreateRequest.name())
                .price(productCreateRequest.price())
                .description(productCreateRequest.description())
                .build();
        return productRepo.save(product);
    }
}
