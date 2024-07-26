package com.demo.coffeeshop;

import com.demo.coffeeshop.domain.Coffee;
import com.demo.coffeeshop.domain.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public Coffee getCoffeeById(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public List<Coffee> getCoffeeByCategory(String category) {
        return coffeeRepository.findByCategory(category);
    }

    public Coffee saveCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public void deleteCoffee(Long id) {
        coffeeRepository.deleteById(id);
    }
}

