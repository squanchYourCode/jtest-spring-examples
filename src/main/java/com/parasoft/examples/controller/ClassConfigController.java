package com.parasoft.examples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * An example controller whose test is expected to use a Class configuration.
 */
@Controller
public class ClassConfigController
{

    /**
     * An MVC handler method which accepts any payload
     */
    @PutMapping(path = "/classconfig")
    public ModelAndView takesText(@RequestBody String body, Model model)
    {
        return new ModelAndView("home.jsp", model.asMap());
    }
}
