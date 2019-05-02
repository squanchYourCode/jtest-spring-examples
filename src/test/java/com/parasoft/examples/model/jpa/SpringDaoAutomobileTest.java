package com.parasoft.examples.model.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * An example JUnit which tests the AutomobileDaoImpl class. The test uses in-memory H2 database, and provides Spring
 * with DataSource, TransactionManager, EntityManager, and other related beans.
 * The AutomobileDaoImpl class also uses the autowired EntityManager.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringDaoAutomobileTest
{

    // Service under test
    @Autowired
    private IAutomobileDao autoDao;

    // JPA EntityManager
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
    @EnableTransactionManagement
    static class Config
    {

        /**
         * The Service under test
         */
        @Bean
        public IAutomobileDao getAutomobileDao()
        {
            return new AutomobileDaoImpl();
        }

        /**
         * A simple DataSource which connects to the in-memory H2 database
         */
        @Bean
        public DataSource getDataSource()
        {
            return new SimpleDriverDataSource(new org.h2.Driver(), "jdbc:h2:mem:testdb");
        }

        /**
         * Create an EntityManagerFactory bean, which scans for Entities and uses the Hibernate H2 dialect
         */
        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
        {
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setDataSource(dataSource);
            entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
            entityManagerFactoryBean.setPackagesToScan("com.parasoft.examples.model.jpa");
            entityManagerFactoryBean.setJpaProperties(hibProperties());
            return entityManagerFactoryBean;
        }

        /**
         * Settings for the EntityManagerFactory bean.
         */
        private Properties hibProperties()
        {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            properties.put("hibernate.show_sql", false);
            properties.put("hibernate.current_session_context_class", "thread");
            return properties;
        }

        /**
         * Spring requires a TransactionManager when tests are annotated as Transactional. This allows Spring
         * to manage the transaction automatically. Even though we are managing the transactions ourselves in this test,
         * Spring still expects a TransactionManager to be present.
         */
        @Bean
        public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory)
        {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
            return transactionManager;
        }

        /**
         * Creates the EntityManager for autowiring into the Dao class, and this test.
         */
        @Bean
        public EntityManager getEntityManager(LocalContainerEntityManagerFactoryBean entityManagerFactory)
        {
            return entityManagerFactory.getObject().createEntityManager();
        }
    }

    /**
     * Add an Automobile to the database, and verify it can be retrieved.
     */
    @Test
    @Transactional
    public void testCreateAndFind()
    {
        Automobile car = createAutomobile();
        autoDao.create(car);

        Automobile found = autoDao.get(car.getId());
        assertNotNull(found);
        assertTrue("unable to retrieve same car", car.equals(found));
    }

    /**
     * Add multiple Automobiles to the database, and verify all can be retrieved as a list.
     */
    @Test
    @Transactional
    public void testCreateAndFindMultiple()
    {
        Automobile car = createAutomobile();
        autoDao.create(car);
        car = createAutomobile();
        autoDao.create(car);

        List<Automobile> cars = autoDao.getAll();
        assertEquals(2, cars.size());
    }

    /**
     * Add an Automobile to the database, then delete it. Verify it can be retrieved after creation, but not after deletion.
     */
    @Test
    @Transactional
    public void testCreateDelete()
    {
        Automobile car = createAutomobile();
        autoDao.create(car);

        Automobile found = autoDao.get(car.getId());
        assertNotNull(found);
        assertTrue("unable to retrieve same car", car.equals(found));
        autoDao.delete(car.getId());
        assertNull(autoDao.get(car.getId()));
        List<Automobile> cars = autoDao.getAll();
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
}
