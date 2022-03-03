package com.neutrinosys.peopledbweb.web.controller;

import com.neutrinosys.peopledbweb.biz.model.Person;
import com.neutrinosys.peopledbweb.data.FileStorageRepository;
import com.neutrinosys.peopledbweb.data.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Controller
@RequestMapping("/people")
@Log4j2
public class PeopleController {

    public static final String DISPO = """
             attachment; filename="%s"
            """;
    private PersonRepository personRepository;
    private FileStorageRepository fileStorageRepository;

    public PeopleController(PersonRepository personRepository,
                            FileStorageRepository fileStorageRepository) {
        this.personRepository = personRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    @ModelAttribute("people")
    public Iterable<Person> getPeople() {
        return personRepository.findAll();
    }

    @ModelAttribute
    public Person getPerson() {
        return new Person();
    }

    @GetMapping
    public String showPeoplePage() {
        return "people";
    }

    @GetMapping("/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource) {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, format(DISPO, resource))
                .body(fileStorageRepository.findByName(resource));
    }

    @PostMapping
    public String savePerson(@Valid Person person, Errors errors, @RequestParam("photoFilename") MultipartFile photoFile) throws IOException {
        log.info(person);
        log.info("Filename " + photoFile.getOriginalFilename());
        log.info("File size: " + photoFile.getSize());
        log.info("Errors" + errors);
        if (!errors.hasErrors()) {
            fileStorageRepository.save(photoFile.getOriginalFilename(), photoFile.getInputStream());
            personRepository.save(person);
            return "redirect:people";
        }
        return "people";
    }

    @PostMapping(params = "delete=true")
    public String deletePeople(@RequestParam Optional<List<Long>> selections) {
        log.info(selections);
        if (selections.isPresent()) {
            personRepository.deleteAllById(selections.get());
        }
        return "redirect:people";
    }

    @PostMapping(params = "edit=true")
    public String editPerson(@RequestParam Optional<List<Long>> selections, Model model) {
        log.info(selections);
        if (selections.isPresent()) {
            Optional<Person> person = personRepository.findById(selections.get().get(0));
            model.addAttribute("person", person);
        }
        return "people";
    }
}
