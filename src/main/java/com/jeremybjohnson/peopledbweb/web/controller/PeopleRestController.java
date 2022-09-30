package com.jeremybjohnson.peopledbweb.web.controller;

import com.jeremybjohnson.peopledbweb.biz.model.Person;
import com.jeremybjohnson.peopledbweb.biz.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/people")
public class PeopleRestController {

    private PersonService personService;

    @Autowired
    public PeopleRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Iterable<Person> getPeople() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable long id) {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable long id) {
        personService.deleteAllById(List.of(id));
    }

    @PostMapping
    public Person savePerson(Person person) {
        Person savedPerson = personService.save(person, null);
        return savedPerson;
    }
}
