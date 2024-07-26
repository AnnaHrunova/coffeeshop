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
    private CoffeeService coffeeService;


    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

    @PostMapping
    public Coffee createCoffee(@RequestBody Coffee coffee) {
        return coffeeService.saveCoffee(coffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffeeDetails) {
        Coffee coffee = coffeeService.getCoffeeById(id);
        if (coffee != null) {
            coffee.setName(coffeeDetails.getName());
            coffee.setPrice(coffeeDetails.getPrice());
            coffee.setDescription(coffeeDetails.getDescription());
            coffee.setImageUrl(coffeeDetails.getImageUrl());
            coffee.setCategory(coffeeDetails.getCategory());
            final Coffee updatedCoffee = coffeeService.saveCoffee(coffee);
            return ResponseEntity.ok(updatedCoffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        Coffee coffee = coffeeService.getCoffeeById(id);
        if (coffee != null) {
            coffeeService.deleteCoffee(coffee.getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{category}")
    public List<Coffee> getCoffeesByCategory(@PathVariable String category) {
        return coffeeService.getCoffeeByCategory(category);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Coffee> updateCoffeeAvailability(@PathVariable Long id, @RequestParam boolean available) {
        Coffee coffee = coffeeService.getCoffeeById(id);
        if (coffee != null) {
            coffee.setAvailable(available);
            final Coffee updatedCoffee = coffeeService.saveCoffee(coffee);
            return ResponseEntity.ok(updatedCoffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
