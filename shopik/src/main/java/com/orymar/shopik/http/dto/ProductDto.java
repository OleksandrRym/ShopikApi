package com.orymar.shopik.http.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(UUID id, String name, BigDecimal price, String description) {}
