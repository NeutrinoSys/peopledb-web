package com.neutrinosys.peopledbweb.web.controller;

import com.neutrinosys.peopledbweb.biz.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @GetMapping
    public String getPeople(Model model) {
        List<Person> people = List.of(
                new Person(10l, "Jake", "Snake", LocalDate.of(1950, 1, 1), new BigDecimal("50000")),
                new Person(20l, "Sarah", "Smith", LocalDate.of(1960, 2, 1), new BigDecimal("60000")),
                new Person(30l, "Johnny", "Jackson", LocalDate.of(1970, 3, 1), new BigDecimal("70000")),
                new Person(40l, "Bobby", "Kim", LocalDate.of(1980, 4, 1), new BigDecimal("80000"))
        );
        model.addAttribute("people", people);
        return "people";
    }
}
