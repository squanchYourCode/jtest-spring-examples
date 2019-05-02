package com.parasoft.examples.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.parasoft.examples.model.Bird;

/**
 * An example JUnit which tests the Non-Spring JdbcBirdDaoImpl class. The test uses in-memory H2 database
 */
public class BirdTest
{
    private JdbcBirdDaoImpl jdbcBirdDaoImpl;

    /**
     * Before each test, re-initialize the database (creating empty tables) and begin a single transaction
     */
    @Before
    public void setupClass()
        throws Exception
    {
        jdbcBirdDaoImpl = new JdbcBirdDaoImpl();
        Connection connection = jdbcBirdDaoImpl.getConnection();
        JdbcHsqlDbUtil.initialize(connection);
    }

    /**
     * Add two birds to the database, and verify all can be retrieved as a list.
     */
    @Test
    public void createBirdsTest()
        throws Exception
    {
        jdbcBirdDaoImpl.create(createBird("Swan", "White", 28.3));
        jdbcBirdDaoImpl.create(createBird("Hawk", "Gray", 15.0));

        Assert.assertEquals(2, count());
    }

    /**
     * Add a bird to the database and verify it can be retrieved after creation with right attributes.
     * Then update one attribute to the database, and verify it can be retrieved right data.
     */
    @Test
    public void updateBirdTest()
    {
        int id = jdbcBirdDaoImpl.create(createBird("Swan", "White", 28.3));
        Bird bird = jdbcBirdDaoImpl.findById(id);
        Assert.assertNotNull(bird);

        bird.setWeight(38.2);
        jdbcBirdDaoImpl.update(bird);

        bird = jdbcBirdDaoImpl.findById(1);
        Assert.assertEquals(38.2, bird.getWeight(), 0);
    }

    /**
     * Add birds to the database, then delete one. Verify it can be retrieved after creation, but not after deletion.
     */
    @Test
    public void deleteBirdTest()
        throws Exception
    {
        int id = jdbcBirdDaoImpl.create(createBird("Swan", "White", 28.3));
        jdbcBirdDaoImpl.create(createBird("Hawk", "Gray", 15.0));
        Assert.assertEquals(2, count());

        jdbcBirdDaoImpl.delete(id);
        Assert.assertEquals(1, count());
    }

    /**
     * Add birds to the database, and verify they can be retrieved by id.
     */
    @Test
    public void findBirdTest()
    {
        int id = jdbcBirdDaoImpl.create(createBird("Swan", "White", 28.3));
        jdbcBirdDaoImpl.create(createBird("Hawk", "Gray", 15.0));

        Bird bird = jdbcBirdDaoImpl.findById(id);
        Assert.assertNotNull(bird);
        Assert.assertEquals("Swan", bird.getType());
        Assert.assertEquals("White", bird.getColor());
        Assert.assertEquals(28.3, bird.getWeight(), 0);
    }

    /**
     * @return the number of rows in the table
     * @throws Exception
     */
    private int count()
        throws Exception
    {
        Statement statement;
        int total = 0;
        try {
            statement = jdbcBirdDaoImpl.getConnection().createStatement();
            String sql = "select count(*) AS total from BIRDS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    private Bird createBird(String type, String color, double weight)
    {
        Bird bird = new Bird();
        bird.setType(type);
        bird.setColor(color);
        bird.setWeight(weight);

        return bird;
    }

}
