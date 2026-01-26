package com.orymar.shopik.service.impl;

import static java.util.Objects.isNull;

import com.orymar.shopik.http.request.ProductCreateRequest;
import com.orymar.shopik.http.request.ProductUpdateRequest;
import com.orymar.shopik.repo.ProductRepo;
import com.orymar.shopik.entity.ProductEntity;
import com.orymar.shopik.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepo productRepo;

  public ProductEntity create(ProductCreateRequest productCreateRequest) {
    ProductEntity product =
        ProductEntity.builder()
            .name(productCreateRequest.name())
            .price(productCreateRequest.price())
            .description(productCreateRequest.description())
            .build();
    return productRepo.save(product);
  }

  public ProductEntity update(Long id, ProductUpdateRequest productUpdateRequest) {
    var product = productRepo.findById(id).get();
    if (isNull(product)) throw new IllegalArgumentException("id is not exist");
    product.setPrice(productUpdateRequest.price());
    product.setDescription(productUpdateRequest.description());
    return product;
  }

  public ProductEntity getById(Long id) {
    var product = productRepo.findById(id).get();
    if (isNull(product)) throw new IllegalArgumentException("id is not exist");

    return product;
  }

  public void delete(Long id) {
    productRepo.deleteById(id);
  }
}
