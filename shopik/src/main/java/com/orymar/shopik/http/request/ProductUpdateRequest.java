package com.orymar.shopik.http.request;

import java.math.BigDecimal;

public record ProductUpdateRequest(BigDecimal price, String description) {}
