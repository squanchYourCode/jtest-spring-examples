package com.parasoft.examples.model.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class StaticAutomobileDao
    implements IAutomobileDao
{

    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager()
    {
        return new StaticAutomobileDao().entityManager;
    }

    @Override
    public void create(Automobile automobile)
    {
        entityManager().persist(automobile);
    }

    @Override
    public Automobile get(long id)
    {
        return entityManager().find(Automobile.class, id);
    }

    @Override
    public void save(Automobile automobile)
    {
        entityManager().persist(automobile);
    }

    @Override
    public void delete(long id)
    {
        entityManager.remove(get(id));
    }

    @Override
    public List<Automobile> getAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Automobile> findByMake(String make)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
