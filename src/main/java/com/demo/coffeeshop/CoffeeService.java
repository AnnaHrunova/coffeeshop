package com.demo.coffeeshop;

import com.demo.coffeeshop.domain.CoffeeEntity;
import com.demo.coffeeshop.domain.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<CoffeeEntity> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public CoffeeEntity getCoffeeById(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public CoffeeEntity saveCoffee(CoffeeEntity coffee) {
        return coffeeRepository.save(coffee);
    }

    public void deleteCoffee(Long id) {
        coffeeRepository.deleteById(id);
    }
}

