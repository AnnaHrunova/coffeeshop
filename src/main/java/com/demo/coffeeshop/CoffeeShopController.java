package com.demo.coffeeshop;

import com.demo.coffeeshop.domain.Coffee;
import com.demo.coffeeshop.domain.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/coffees")
public class CoffeeShopController {

    @Autowired
    private CoffeeRepository coffeeRepository;


    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    @PostMapping
    public Coffee createCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffeeDetails) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            Coffee coffee = optionalCoffee.get();
            coffee.setName(coffeeDetails.getName());
            coffee.setPrice(coffeeDetails.getPrice());
            coffee.setDescription(coffeeDetails.getDescription());
            coffee.setImageUrl(coffeeDetails.getImageUrl());
            coffee.setCategory(coffeeDetails.getCategory());
            final Coffee updatedCoffee = coffeeRepository.save(coffee);
            return ResponseEntity.ok(updatedCoffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            coffeeRepository.delete(optionalCoffee.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{category}")
    public List<Coffee> getCoffeesByCategory(@PathVariable String category) {
        return coffeeRepository.findByCategory(category);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Coffee> updateCoffeeAvailability(@PathVariable Long id, @RequestParam boolean available) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            Coffee coffee = optionalCoffee.get();
            coffee.setAvailable(available);
            final Coffee updatedCoffee = coffeeRepository.save(coffee);
            return ResponseEntity.ok(updatedCoffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
