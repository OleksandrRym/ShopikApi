package com.orymar.shopik.http.dto;

import java.math.BigDecimal;

public record ProductDto(Long id, String name, BigDecimal price, String description) {}
