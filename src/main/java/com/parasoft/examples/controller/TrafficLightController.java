
package com.parasoft.examples.controller;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parasoft.examples.service.ColorService;

@RestController
@RequestMapping("/light/{color}")
public class TrafficLightController
{
    @Autowired
    ColorService service;

    @GetMapping
    public Color getColor(@PathVariable("color") String color)
    {
        return service.adapter(color);
    }
}
