package com.orymar.shopik.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "product")
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

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
