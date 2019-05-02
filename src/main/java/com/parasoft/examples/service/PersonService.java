package com.parasoft.examples.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.parasoft.examples.model.Person;

/**
 * An example Service which manages Person objects.<br/>
 * Default values are added when this service is constructed.
 */
public class PersonService
    implements IPersonService
{
    private Map<Integer, Person> people = new HashMap<>();

    public PersonService()
    {
        people.put(1, new Person("John", 32));
    }

    @Override
    public Person getPerson(int id)
    {
        return people.get(id);
    }

    @Override
    public Collection<Person> getAllPeople()
    {
        return people.values();
    }

    @Override
    public Person findPerson(String name)
    {
        for (Person person : people.values()) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
}
