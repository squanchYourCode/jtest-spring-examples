package com.parasoft.examples.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * A controller used to demonstrate session data
 */
@Controller
@SessionAttributes("username")
public class SessionController
{
    @PostMapping
    public ModelAndView login(@ModelAttribute("username") String name, @SessionAttribute("password") String pass, Model model)
    {
        model.addAttribute("username", name);
        model.addAttribute("password", "*******");
        return new ModelAndView("login.jsp", model.asMap());
    }

    @GetMapping
    public ModelAndView getRole(@SessionAttribute("role") String role, Model model)
    {
        model.addAttribute("username", role);
        return new ModelAndView("currentUser.jsp", model.asMap());
    }

    @GetMapping("/save")
    public ModelAndView save(HttpSession session, Model model)
    {
        Enumeration<String> iter = session.getAttributeNames();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            model.addAttribute(key, session.getAttribute(key));
        }
        return new ModelAndView("save.jsp", model.asMap());
    }
}
