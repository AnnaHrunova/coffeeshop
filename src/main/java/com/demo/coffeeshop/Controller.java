package com.demo.coffeeshop;

import com.demo.coffeeshop.domain.CoffeeEntity;
import com.demo.coffeeshop.domain.CoffeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping
    public String listCoffees(Model model) {
        List<CoffeeEntity> coffees = coffeeRepository.findAll();
        model.addAttribute("coffees", coffees);
        return "coffee_list";
    }

    @GetMapping("/new")
    public String showCoffeeForm(Model model) {
        model.addAttribute("coffee", new CoffeeEntity());
        return "coffee_form";
    }

    @PostMapping
    public String saveCoffee(@Valid @ModelAttribute CoffeeEntity coffee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "coffee_form";
        }
        coffee.setAddedDate(LocalDateTime.now());
        coffeeRepository.save(coffee);
        return "redirect:/coffees";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<CoffeeEntity> coffee = coffeeRepository.findById(id);
        if (coffee.isPresent()) {
            model.addAttribute("coffee", coffee.get());
            return "coffee_form";
        } else {
            return "redirect:/coffees";
        }
    }

    @PostMapping("/{id}")
    public String updateCoffee(@PathVariable("id") Long id, @Valid @ModelAttribute CoffeeEntity coffee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "coffee_form";
        }
        coffee.setId(id);
        coffeeRepository.save(coffee);
        return "redirect:/coffees";
    }

    @GetMapping("/{id}/delete")
    public String deleteCoffee(@PathVariable("id") Long id) {
        coffeeRepository.deleteById(id);
        return "redirect:/coffees";
    }
}
