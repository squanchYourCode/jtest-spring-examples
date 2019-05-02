package com.parasoft.examples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * An example controller whose test is expected to use an XML configuration.
 */
@Controller
public class XmlConfigController
{

    /**
     * An MVC handler method which accepts any payload
     */
    @PutMapping(path = "/xmlconfig")
    public ModelAndView takesText(@RequestBody String body, Model model)
    {
        return new ModelAndView("home.jsp", model.asMap());
    }
}
