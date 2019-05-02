package com.parasoft.examples.controller.construct;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parasoft.examples.model.IPet;
import com.parasoft.examples.service.PetService;

/**
 * An example controller which uses Constructor Dependency Injection to acquire its dependencies.
 */
@Controller
public class ConstructorBeanController
{

    // The constructor-provided Bean
    private PetService petService;

    @Autowired
    public ConstructorBeanController(PetService petService)
    {
        this.petService = petService;
    }

    /**
     * An MVC handler method which gets all Pets from PetService
     */
    @RequestMapping("/petexample")
    public ResponseEntity<Collection<IPet>> handler(Model model)
    {
        Collection<IPet> pets = petService.getAllPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }
}
