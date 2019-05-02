package com.parasoft.examples.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parasoft.examples.model.IPet;
import com.parasoft.examples.model.Person;
import com.parasoft.examples.service.MultiService;

/**
 * An example controller with a dependency on MultiService. MultiService has
 * dependencies of its own. UTA will generate Beans for MultiService, as well as
 * all of its dependencies.
 */
@Controller
@RequestMapping("/multi")
public class MultiServiceController
{

    // The Autowired MultiService
    @Autowired
    MultiService service;

    /**
     * An MVC handler method which retrieves a person by name, via MultiService
     *
     * @param name
     * @return Person
     * @throws BadRequestException iff not found
     */
    @GetMapping("/person/{name}")
    public @ResponseBody Person getPersonByName(@PathVariable("name") String name)
        throws BadRequestException
    {
        Person person = service.findPerson(name);
        if (person == null) {
            throw new BadRequestException("not found");
        }
        return person;
    }

    /**
     * An MVC handler method which retrieves a pet by name, via MultiService
     *
     * @param name
     * @return Pet
     * @throws BadRequestException iff not found
     */
    @GetMapping("/pet/{name}")
    public @ResponseBody IPet getPetName(@PathVariable("name") String name)
        throws BadRequestException
    {
        IPet person = service.findPet(name);
        if (person == null) {
            throw new BadRequestException("not found");
        }
        return person;
    }

    /**
     * Handles BadRequestExceptions being thrown from handler methods in this class
     * by responding with a 400 Bad Request.
     */
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody String handleException(BadRequestException e, HttpServletResponse response)
    {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return e.getMessage();
    }
}
