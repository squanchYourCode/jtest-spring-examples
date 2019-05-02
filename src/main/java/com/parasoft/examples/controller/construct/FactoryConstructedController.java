package com.parasoft.examples.controller.construct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * An example controller which provides a Factory-method to instantiate the Bean.
 * Spring will use Factory-method Dependency Injection to provide the simple value.
 */
@Controller
public class FactoryConstructedController
{

    // The factory-provided value
    private int val;

    private FactoryConstructedController(int val)
    {
        this.val = val;
    }

    /**
     * Factory method for constructing this controller
     *
     * @param val
     */
    public static FactoryConstructedController getTempController(int val)
    {
        return new FactoryConstructedController(val);
    }

    /**
     * An MVC handler method which adds the factory-provided value to the Model
     */
    @RequestMapping("/factory")
    public ModelAndView handler(Model model)
    {
        model.addAttribute("val", val);
        return new ModelAndView("factory.jsp", model.asMap());
    }
}
