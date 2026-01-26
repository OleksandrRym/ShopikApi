package com.orymar.shopik.service;

import com.orymar.shopik.http.request.ProductCreateRequest;
import com.orymar.shopik.http.request.ProductUpdateRequest;
import com.orymar.shopik.entity.ProductEntity;

public interface ProductService {
  ProductEntity update(Long id, ProductUpdateRequest productUpdateRequest);

  ProductEntity getById(Long id);

  void delete(Long id);

  ProductEntity create(ProductCreateRequest productCreateRequest);
}
