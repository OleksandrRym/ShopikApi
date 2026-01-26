package com.orymar.shopik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ShopikApplication {
  public static void main(String[] args) {
    SpringApplication.run(ShopikApplication.class, args);
  }
}
