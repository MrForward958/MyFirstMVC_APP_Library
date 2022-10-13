package ru.alishev.springcourse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.DAO.PersonDAO;
import ru.alishev.springcourse.Models.Person;
import ru.alishev.springcourse.until.PersonValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String listPerson(Model model){
        model.addAttribute("people",personDAO.addListPerson());
        return "person/listPerson";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable ("id") int id, Model model) {
        Person person = personDAO.show(id);
        model.addAttribute("person", person);
        model.addAttribute("books",personDAO.getBookByPersonId(id));
        return "person/showPerson";
    }
    @GetMapping("/new")
    public String createPerson(@ModelAttribute("person")Person person){
        return "person/new";
    }

    @PostMapping()
    public String savePerson(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "person/new";
        personDAO.save(person);
        return "redirect:/person";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("updatePerson",personDAO.show(id));
        return "person/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id")int id, @ModelAttribute @Valid Person person,
                               BindingResult bindingResult ){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "person/new";
        personDAO.update(id,person);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/person";
    }

}
