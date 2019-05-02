package com.parasoft.examples.dao;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.parasoft.examples.model.Bird;
import com.parasoft.examples.service.BirdService;

/**
 * An example JUnit which tests the JdbcBirdDao class. The test uses in-memory H2 database, and provides Spring
 * with DataSource, DataSourceTransactionManager, JdbcTemplate, and other related beans.
 * The JdbcBirdDao class also uses the autowired JdbcTemplate.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
public class JdbcBirdDaoTest
{
    @Autowired
    private BirdService birdService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup()
        throws Exception
    {
        JdbcHsqlDbUtil.initialize(JdbcHsqlDbUtil.getConnection());
    }

    @Configuration
    static class Config
    {
        @Bean
        public BirdService getBirdService()
        {
            return new BirdService();
        }

        @Bean
        public JdbcTemplate getjdbcTemplate(DataSource dataSource)
        {
            return new JdbcTemplate(dataSource);
        }

        @Bean
        public DataSource getDataSource()
        {
            return new SimpleDriverDataSource(new org.h2.Driver(), "jdbc:h2:mem:jdbctestdb");
        }

        @Bean
        public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource)
        {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean
        public IBirdDao getBirdDao()
        {
            return new JdbcBirdDao();
        }
    }

    @Test
    public void createBirdsTest()
    {
        assertBirdCount(0);

        birdService.create(createBird("Swan", "White", 28.3));
        assertBirdCount(1);
    }

    @Test
    public void deleteBirdTest()
    {
        assertBirdCount(0);

        int id = birdService.create(createBird("Swan", "White", 28.3));
        birdService.create(createBird("Hawk", "Gray", 15.0));
        assertBirdCount(2);

        birdService.delete(id);
        assertBirdCount(1);
    }

    @Test
    public void findBirdTest()
    {
        int id = birdService.create(createBird("Swan", "White", 28.3));

        Bird bird = birdService.findById(id);
        Assert.assertEquals("White", bird.getColor());
        Assert.assertEquals("Swan", bird.getType());
        Assert.assertEquals(28.3, bird.getWeight(), 0);
    }

    @Test
    public void updateBirdTest()
    {
        int id = birdService.create(createBird("Swan", "White", 28.3));

        Bird bird = birdService.findById(id);
        bird.setWeight(38.2);
        birdService.update(bird);

        bird = birdService.findById(id);
        Assert.assertEquals("White", bird.getColor());
        Assert.assertEquals("Swan", bird.getType());
        Assert.assertEquals(38.2, bird.getWeight(), 0);
    }

    private Bird createBird(String type, String color, double weight)
    {
        Bird bird = new Bird();
        bird.setType(type);
        bird.setColor(color);
        bird.setWeight(weight);

        return bird;
    }

    private void assertBirdCount(int count)
    {
        long countAfterInsert = jdbcTemplate.queryForObject("select count(*) from BIRDS", Long.class);
        Assert.assertEquals(count, countAfterInsert);
    }
}
