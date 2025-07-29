package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping("/pizzas")
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas";
    }

    @GetMapping("/pizza/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("pizza", repository.findById(id).get());
        return "pizza";
    }

    @GetMapping("/search")
    public String show(@RequestParam(name = "query") String query, Model model) {

        List<Pizza> pizzas = repository.findByNameContainingIgnoreCase(query);

        model.addAttribute("pizzas", pizzas);
        return "pizzas";
    }

}
