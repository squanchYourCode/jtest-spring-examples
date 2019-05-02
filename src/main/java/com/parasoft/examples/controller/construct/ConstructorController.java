package com.parasoft.examples.controller.construct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * An example controller which requires a simple value to construct
 */
@Controller
public class ConstructorController
{

    // The constructor-provided value
    private String val;

    public ConstructorController(String val)
    {
        this.val = val;
    }

    /**
     * An MVC handler method which adds the constructor-provided value to the Model
     */
    @RequestMapping("/constructor")
    public ModelAndView handler(Model model)
    {
        model.addAttribute("val", val);
        return new ModelAndView("constructor.jsp", model.asMap());
    }
}
