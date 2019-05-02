package com.parasoft.examples.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * An example test which uses the Spring CrudRepository to manage Automobile entities with JPA.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringCrudAutomobileTest
{
    private static final String PERSISTENCE_UNIT = "entityMgrEx";

    @Autowired
    private CrudRepository<Automobile, Long> autoRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * Before each test, re-initialize the database (creating empty tables) and begin a single transaction
     */
    @Before
    public void setup()
        throws Exception
    {
        HsqlDbUtil.initialize(HsqlDbUtil.getConnection());
        entityManager.getTransaction().begin();
    }

    /**
     * After each test, rollback all changes made to the database - we don't need to commit them.
     */
    @After
    public void tearDown()
    {
        entityManager.getTransaction().rollback();
    }

    // The Java Configuration
    @Configuration
    static class Config
    {

        @Bean
        public CrudRepository<Automobile, Long> getAutoRepository(EntityManager entityManager)
        {
            return new SimpleJpaRepository<>(Automobile.class, entityManager);
        }

        @Bean
        public EntityManager getEntityManager()
        {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            return factory.createEntityManager();
        }
    }

    /**
     * Add an Automobile to the database, and verify it can be retrieved.
     */
    @Test
    public void testCreateAndFind()
    {
        Automobile car = createAutomobile();
        autoRepository.save(car);

        assertEquals(car, autoRepository.findOne(car.getId()));
        logAutos();
    }

    /**
     * Add multiple Automobiles to the database, and verify all can be retrieved as a list.
     */
    @Test
    public void testCreateAndFindMultiple()
    {
        Automobile car = createAutomobile();
        autoRepository.save(car);
        car = createAutomobile();
        autoRepository.save(car);

        List<Automobile> cars = (List<Automobile>)autoRepository.findAll();
        assertEquals(2, cars.size());
        logAutos();
    }

    /**
     * Add an Automobile to the database, then delete it. Verify it can be retrieved after creation, but not after deletion.
     */
    @Test
    public void testCreateDelete()
    {
        Automobile car = createAutomobile();
        autoRepository.save(car);

        assertEquals(car, autoRepository.findOne(car.getId()));
        autoRepository.delete(car);
        assertNull(autoRepository.findOne(car.getId()));
        List<Automobile> cars = (List<Automobile>)autoRepository.findAll();
        assertEquals(0, cars.size());
    }

    /**
     * Utility method for creating the complex Automobile POJO
     */
    private Automobile createAutomobile()
    {
        Automobile car = new Automobile();
        car.setColor("red");
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setMileage(12345);
        System.out.println("Creating Automobile: " + car);
        return car;
    }

    /**
     * Dump all Automobile objects from the database to the Console.
     */
    private void logAutos()
    {
        Query query = entityManager.createQuery("from Automobile");
        for (Object o : query.getResultList()) {
            System.out.println("AUTO: " + o);
        }
    }
}
