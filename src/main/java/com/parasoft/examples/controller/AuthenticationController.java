package com.parasoft.examples.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * An example controller which demonstrates use of Spring-security
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController
{

    /**
     * An example handler method which accepts Authentication as a parameter
     */
    @GetMapping
    public ModelAndView getAuthentication(Model model, Authentication authentication)
    {
        model.addAttribute("authName", authentication.getName());
        return new ModelAndView("auth.jsp", model.asMap());
    }

    /**
     * An example handler method which retrieves an Authentication through a static call
     */
    @GetMapping("/static")
    public ModelAndView getAuthenticationStatic(Model model)
    {
        Authentication authentication = getAuth();
        model.addAttribute("authName", authentication.getName());
        return new ModelAndView("auth.jsp", model.asMap());
    }

    private Authentication getAuth()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * An example handler method which accepts Principal as a parameter
     */
    @GetMapping("/principal")
    public ModelAndView getPrincipal(Model model, Principal principal)
    {
        model.addAttribute("authName", principal.getName());
        return new ModelAndView("auth.jsp", model.asMap());
    }

    /**
     * An example handler method which retrieves a Principal through a static call
     */
    @GetMapping("/principal/static")
    public ModelAndView getPrincipalStatic(Model model)
    {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authName", principal.getName());
        return new ModelAndView("auth.jsp", model.asMap());
    }
}
