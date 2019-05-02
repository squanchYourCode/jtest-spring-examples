package com.parasoft.examples.model.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * A simple implementation of IAutomobileDao which uses JPA's EntityManager directly.
 */
public class AutomobileDaoImpl
    implements IAutomobileDao
{

    @Autowired
    EntityManager entityManager;

    @Override
    public void create(Automobile automobile)
    {
        entityManager.persist(automobile);
    }

    /**
     * @jtest.factory
     */
    public static Automobile create(long id, String make, String model, String color, int mileage)
    {
        Automobile ret = new Automobile(id);
        ret.setMake(make);
        ret.setModel(model);
        ret.setColor(color);
        ret.setMileage(mileage);
        return ret;
    }

    @Override
    public Automobile get(long id)
    {
        Automobile ret = entityManager.find(Automobile.class, id);
        return ret;
    }

    @Override
    public void save(Automobile automobile)
    {
        entityManager.merge(automobile);
    }

    @Override
    public void delete(long id)
    {
        Automobile automobile = get(id);
        if (automobile != null && entityManager.contains(automobile)) {
            entityManager.remove(automobile);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Automobile> getAll()
    {
        return entityManager.createQuery("from Automobile").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Automobile> findByMake(String make)
    {
        return entityManager.createQuery("from Automobile where make = :make").setParameter("make", make).getResultList();
    }

}
