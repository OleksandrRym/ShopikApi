package com.orymar.shopik.http.api;

import com.orymar.shopik.http.dto.ProductDto;
import com.orymar.shopik.http.mapper.ProductMapper;
import com.orymar.shopik.http.request.ProductCreateRequest;
import com.orymar.shopik.http.request.ProductUpdateRequest;
import com.orymar.shopik.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ToDO: create exceptions handler

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productServiceImpl;
  private final ProductMapper mapper;

  @PostMapping
  public ResponseEntity<ProductDto> create(@RequestParam ProductCreateRequest request) {
    var product = productServiceImpl.create(request);
    var dto = mapper.toDto(product);
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/{id}")
  public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestParam ProductUpdateRequest request) {
    var product = productServiceImpl.update(id, request);
    var dto = mapper.toDto(product);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> get(@PathVariable Long id) {
    var product = productServiceImpl.getById(id);
    var dto = mapper.toDto(product);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    productServiceImpl.delete(id);
    return ResponseEntity.ok().build();
  }
}
