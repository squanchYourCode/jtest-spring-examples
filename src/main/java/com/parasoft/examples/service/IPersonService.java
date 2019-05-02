package com.parasoft.examples.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.parasoft.examples.model.Person;

@Service
public interface IPersonService
{
    Person getPerson(int id);

    Collection<Person> getAllPeople();

    Person findPerson(String name);
}
