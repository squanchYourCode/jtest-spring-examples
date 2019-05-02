package com.parasoft.examples.controller;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parasoft.examples.model.Person;

/**
 * An example MVC controller which manages people through the PersonService.<br/>
 * This controller extends AbstractPeopleController in order to demonstrate test
 * construction with class hierarchies.<br/>
 * Also provides examples of response messages, in the form of ResponseEntity and
 * ResponseBody-annotated
 */
@Controller
@RequestMapping("/people")
public class PeopleController
    extends AbstractPeopleController
{

    /**
     * An MVC handler method which adds all People to the Model
     *
     * @param model
     */
    @GetMapping
    public ModelAndView people(Model model)
    {
        Collection<Person> peoples = personService.getAllPeople();
        if (peoples.isEmpty()) {
            model.addAttribute("john", "doe");
            model.addAttribute("fooo", "bar");
        } else {
            for (Person person : personService.getAllPeople()) {
                model.addAttribute(person.getName(), person.getAge());
            }
        }
        return new ModelAndView("people.jsp", model.asMap());
    }

    /**
     * An MVC handler method which gets a Person by id
     *
     * @param id
     * @return The Person as a ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") int id)
    {
        Person person = personService.getPerson(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * An MVC handler method which searches for a Person by name. HttpSession and Locale are unused.
     *
     * @param name
     * @return The Person as a ResponseEntity if found
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<Person> search(@PathVariable("name") String name, HttpSession session, Locale locale)
    {
        Person person = personService.findPerson(name);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * An MVC handler method which searches for a person using a regular expression path parameter
     *
     * @param query
     * @return The Person as a ResponseEntity if found
     */
    @GetMapping("/find/{query:[a-zA-Z0-9]+}")
    public ResponseEntity<Person> findByRegex(@PathVariable("query") String query)
    {
        Person person = personService.findPerson(query);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * An MVC handler method which retrieves a person using a path parameter, and accepts an ant-style path segment.
     *
     * @param id The Person id
     * @param reportType The ant-style path segment
     * @return The Person as a ResponseEntity if found, 404 otherwise
     */
    @GetMapping("/{id}/report.*")
    public ResponseEntity<Person> reportByWildcard(@PathVariable("id") int id, @PathVariable String reportType)
    {
        Person person = personService.getPerson(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * An MVC handler method which gets a Person by name, specified as a path parameter.
     *
     * @param name
     * @return The Person as a ResponseEntity if found
     * @throws BadRequestException if not found
     */
    @GetMapping("/byname/{name}")
    public @ResponseBody Person getPersonByName(@PathVariable("name") String name)
        throws BadRequestException
    {
        Person person = personService.findPerson(name);
        if (person == null) {
            throw new BadRequestException("not found");
        }
        return person;
    }
}
