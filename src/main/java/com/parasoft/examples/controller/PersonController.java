package com.parasoft.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.parasoft.examples.model.Person;
import com.parasoft.examples.service.PersonService;

/**
 * Example of an MVC controller with a dependency - in this case, PersonService<br/>
 * All handlers in this controller act on a single Person object, mapped to the Model by the "id" path parameter.
 */
@Controller
@RequestMapping("/people/{id}")
public class PersonController
{

    // The autowired PersonService Bean
    @Autowired
    private PersonService personService;

    /**
     * Retrieves a Person by id. This method populates the Person into the Model
     * argument for each handler method in this controller.
     * @param id
     * @return The Person, or null if not found
     */
    @ModelAttribute
    public Person getPerson(@PathVariable("id") int id)
    {
        return personService.getPerson(id);
    }

    /**
     * An MVC handler method that adds a single Person to the Model
     *
     * @param person Populated by getPerson(int)
     * @param model
     */
    @GetMapping
    public ModelAndView getPerson(@ModelAttribute Person person, Model model)
    {
        model.addAttribute("person", person);
        return new ModelAndView("people.jsp", model.asMap());
    }
}
