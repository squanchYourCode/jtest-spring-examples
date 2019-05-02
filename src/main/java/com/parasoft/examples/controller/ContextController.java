package com.parasoft.examples.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * An example controller which implements ApplicationContextAware. UTA does not
 * create a Bean method for the ApplicationContext.
 */
@Controller
public class ContextController
    implements ApplicationContextAware
{

    @SuppressWarnings("unused")
    private ApplicationContext _context;

    @RequestMapping(path = "/context")
    public String mvcSubpathHome()
    {
        return "context.jsp";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        _context = applicationContext;
    }
}
