package com.parasoft.examples.model.jpa;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Non-Spring JUnit for the Automobile Entity, when managed by JPA.
 * This is an example test that validates the ORM aspects of Automobile.
 */
public class AutomobileTest
{
    private static final String PERSISTENCE_UNIT = "entityMgrEx";

    private static EntityManagerFactory emf;

    // The JPA EntityManager
    private EntityManager entityManager;

    @BeforeClass
    public static void setupClass()
    {
        System.out.println("Creating EntityManagerFactory for Persistence Unit '" + PERSISTENCE_UNIT + "'");
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    @AfterClass
    public static void teardownClass()
    {
        System.out.println("Closing EntityManagerFactory");
        emf.close();
    }

    /**
     * Before each test, we re-initialize the H2 (in-memory) database. This creates empty table(s).
     */
    @Before
    public void setup()
        throws Exception
    {
        System.out.println("Creating EntityManager");
        entityManager = emf.createEntityManager();
        HsqlDbUtil.initialize(HsqlDbUtil.getConnection());
    }

    /**
     * After each test, we dump the list of automobiles in the database for debugging purposes
     */
    @After
    public void teardown()
        throws Exception
    {
        try {
            System.out.println("Teardown started, entityManager=" + entityManager);
            entityManager.getTransaction().begin();
            entityManager.flush();
            logAutos();
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Teardown complete, entityManager=" + entityManager);
        } catch (Exception e) {
            System.out.println("Teardown failed");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test persisting the database. Does not try to find the persisted Automobile
     */
    @Test
    public void testCreate()
    {
        Automobile car = createAutomobile();
        entityManager.persist(car);
    }

    /**
     * Persist an Automobile to the database, then verify it can be retrieved.
     */
    @Test
    public void testFind()
    {
        Automobile car = createAutomobile();
        entityManager.persist(car);

        // We need a transaction for the persist() to write to the DB
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        Automobile found = entityManager.find(Automobile.class, car.getId());
        assertNotNull("Car not found", found);
        System.out.println("Found Automobile: " + found);
    }

    /**
     * Persist an Automobile to the database, then verify it can be retrieved by reference.
     */
    @Test
    public void testGetReference()
    {
        Automobile car = createAutomobile();
        entityManager.persist(car);

        // We need a transaction for the persist() to write to the DB
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        Automobile found = entityManager.getReference(Automobile.class, car.getId());
        assertNotNull("Car not found", found);
        System.out.println("Found Automobile: " + found);
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
