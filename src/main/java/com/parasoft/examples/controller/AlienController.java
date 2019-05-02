package com.parasoft.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.parasoft.examples.model.Alien;
import com.parasoft.examples.model.Person;
import com.parasoft.examples.model.Planet;

@Controller
public class AlienController
{
    @Autowired
    protected Alien alien;

    @GetMapping("/alien")
    public String createAlien()
    {
        Planet mars = Planet.MARS;
        Person person = new Person("Tom", 35);
        Alien martian = new Alien(person, mars);
        martian.toString();
        return Alien.speak();
    }

    private void privateMethod() {}

    private void privateParameterizedMethod(String param) {}
}
