package com.parasoft.examples.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parasoft.examples.model.Cat;
import com.parasoft.examples.model.Dog;
import com.parasoft.examples.model.IPet;

/**
 * An example Service which manages Pet objects.<br/>
 * Default values are added when this service is constructed.
 */
public class PetService
    implements IPetService
{
    private Map<Integer, IPet> pets = new HashMap<>();

    public PetService()
    {
        pets.put(1, new Dog("Spot"));
        pets.put(2, new Cat("Fluffy"));
    }

    @Override
    public IPet getPet(int id)
    {
        return pets.get(id);
    }

    @Override
    public Collection<IPet> getAllPets()
    {
        return pets.values();
    }

    @Override
    public IPet findPet(String name)
    {
        for (IPet pet : pets.values()) {
            if (pet.getName().equals(name)) {
                return pet;
            }
        }
        return null;
    }

    @Override
    public Collection<IPet> findPetByType(Class<IPet> type)
    {
        List<IPet> ret = new ArrayList<>();
        for (IPet pet : pets.values()) {
            if (type.isInstance(pet)) {
                ret.add(type.cast(pet));
            }
        }
        return ret;
    }
}
