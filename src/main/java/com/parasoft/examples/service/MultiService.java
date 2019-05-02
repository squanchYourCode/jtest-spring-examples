package com.parasoft.examples.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.parasoft.examples.model.IPet;
import com.parasoft.examples.model.Person;

/**
 * An example Service with multiple dependencies.
 */
public class MultiService
    implements IPersonService, IPetService
{

    // An autowired dependency
    @Autowired
    private IPersonService personServ;

    // A Constructor-provided dependency
    private IPetService petServ;

    public MultiService(IPetService petService)
    {
        petServ = petService;
    }

    @Override
    public Person getPerson(int id)
    {
        return personServ.getPerson(id);
    }

    @Override
    public Collection<Person> getAllPeople()
    {
        return personServ.getAllPeople();
    }

    @Override
    public Person findPerson(String name)
    {
        return personServ.findPerson(name);
    }

    @Override
    public IPet getPet(int id)
    {
        return petServ.getPet(id);
    }

    @Override
    public Collection<IPet> getAllPets()
    {
        return petServ.getAllPets();
    }

    @Override
    public IPet findPet(String name)
    {
        return petServ.findPet(name);
    }

    @Override
    public Collection<IPet> findPetByType(Class<IPet> clazz)
    {
        return petServ.findPetByType(clazz);
    }
}
