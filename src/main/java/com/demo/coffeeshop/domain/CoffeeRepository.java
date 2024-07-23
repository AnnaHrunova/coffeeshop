package com.demo.coffeeshop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {
}
