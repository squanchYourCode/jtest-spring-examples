package com.parasoft.examples.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parasoft.examples.service.PersonService;

/**
 * An example abstract class which controller classes can extend.
 */
@Controller
public abstract class AbstractPeopleController
{

    // The autowired PersonService Bean
    @Autowired
    protected PersonService personService;

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
