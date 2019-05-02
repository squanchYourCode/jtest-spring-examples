package com.parasoft.examples.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * An example controller with handler methods that accept and produce messages with various content-types.
 */
@Controller
public class PayloadController
{

    /**
     * An MVC handler method which accepts any request body and echoes the request into the response.
     *
     * @param body
     * @return The echoed request
     */
    @PutMapping(path = "/text")
    public ResponseEntity<String> takesText(@RequestBody String body, Model model)
    {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * An MVC handler method which accepts and produces JSON and echoes the request into the response.
     *
     * @param body
     * @return The echoed JSON request
     */
    @PostMapping(path = "/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> takesJson(@RequestBody String body, Model model)
    {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * An MVC handler method which accepts and produces XML and echoes the request into the response.
     *
     * @param body
     * @return The echoed XML request
     */
    @PostMapping(path = "/xml", consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<String> takesXml(@RequestBody String body, Model model)
    {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    /**
     * An MVC handler method which accepts any request body as an HttpEntity.
     *
     * @param body
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ModelAndView specialParam(HttpEntity<String> body, Model model)
    {
        return new ModelAndView("home.jsp", model.asMap());
    }
}
