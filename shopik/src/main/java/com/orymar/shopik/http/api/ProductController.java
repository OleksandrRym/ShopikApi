package com.orymar.shopik.http.api;

import com.orymar.shopik.http.request.ProductCreateRequest;
import com.orymar.shopik.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;
  private final ProductMapper mapper;

  @PostMapping
  public ResponseEntity<ProductDTO> create(@RequestMapping ProductCreateRequest request) {
    log.info("");
  }
}
