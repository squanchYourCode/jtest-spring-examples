package com.parasoft.examples.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parasoft.examples.model.jpa.Automobile;
import com.parasoft.examples.model.jpa.StaticAutomobileDao;

/**
 * Example controller that makes static calls to retrieve data.
 */
@Controller
@RequestMapping("/automobiles")
public class AutomobileController
{

    @GetMapping("/{id}")
    public ResponseEntity<Automobile> getAutomobile(@PathVariable("id") long id)
    {
        Automobile automobile = StaticAutomobileDao.entityManager().find(Automobile.class, id);
        if (automobile != null) {
            return new ResponseEntity<>(automobile, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
