package com.parasoft.examples.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.parasoft.examples.model.IPet;

@Service
public interface IPetService
{
    IPet getPet(int id);

    Collection<IPet> getAllPets();

    IPet findPet(String name);

    Collection<IPet> findPetByType(Class<IPet> clazz);
}
