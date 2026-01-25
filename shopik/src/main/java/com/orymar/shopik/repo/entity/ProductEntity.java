package com.orymar.shopik.repo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
@Builder
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  private BigDecimal price;

  private String description;

  private Instant createdAt = Instant.now();

  private Instant updatedAt;

  @PreUpdate
  private void preUpdatedAt() {
    this.updatedAt = Instant.now();
  }

  @PrePersist
  private void prePersist() {
    this.createdAt = Instant.now();
    this.updatedAt = Instant.now();
  }
}
