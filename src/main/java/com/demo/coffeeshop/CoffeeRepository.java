package com.demo.coffeeshop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {
}
