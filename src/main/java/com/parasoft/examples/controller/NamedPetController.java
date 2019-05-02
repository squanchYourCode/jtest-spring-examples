package com.parasoft.examples.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parasoft.examples.model.Cat;
import com.parasoft.examples.model.Dog;
import com.parasoft.examples.model.IPet;
import com.parasoft.examples.service.IPetService;

/**
 * An example controller with a Bean name, which manages Pet objects.
 */
@Controller("NamedPet")
public class NamedPetController
{

    // The PetService, which Spring will inject based on the Bean name
    @Resource
    @Qualifier("PetService")
    private IPetService petService;

    /**
     * An MVC handler method which retrieves a Pet by name.
     *
     * @param name
     * @return ResponseEntity
     */
    @RequestMapping("/pets/{name}")
    public ResponseEntity<IPet> getByName(@PathVariable("name") String name)
    {
        IPet pet = petService.findPet(name);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    /**
     * An MVC handler method which retrieves all pets by type
     *
     * @param name either 'cat' or 'dog'
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/pets")
    public ResponseEntity<Collection<IPet>> getByType(@RequestParam("type") String type)
    {
        Class<? extends IPet> clazz = type.equals("cat") ? Cat.class : Dog.class;
        Collection<IPet> pets = petService.findPetByType((Class<IPet>)clazz);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }
}
