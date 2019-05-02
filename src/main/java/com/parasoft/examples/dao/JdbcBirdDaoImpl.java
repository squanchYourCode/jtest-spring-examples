package com.parasoft.examples.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.parasoft.examples.model.Bird;

/**
 * A simple implementation of IBirdDao which connects to a database directly through JDBC
 */
public class JdbcBirdDaoImpl
    implements IBirdDao
{

    Connection connection = null;

    public JdbcBirdDaoImpl() throws Exception
    {
        connection = JdbcHsqlDbUtil.getConnection();
    }

    public Connection getConnection()
        throws Exception
    {
        return connection;
    }

    @Override
    public Bird findById(int id)
    {
        Statement statement;
        Bird bird = new Bird();
        try {
            statement = connection.createStatement();
            String sql = "select type, color, weight, id from BIRDS where id =" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                bird.setType(resultSet.getString("type"));
                bird.setColor(resultSet.getString("color"));
                bird.setWeight(resultSet.getDouble("weight"));
                bird.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bird;
    }

    @Override
    public int create(Bird bird)
    {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("insert into BIRDS(type, color, weight) values(?,?,?)");
            preparedStatement.setString(1, bird.getType());
            preparedStatement.setString(2, bird.getColor());
            preparedStatement.setDouble(3, bird.getWeight());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                bird.setId(generatedKeys.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bird.getId();
    }

    @Override
    public void update(Bird bird)
    {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("update BIRDS set type = ?, color = ?, weight = ? where id = ?");
            preparedStatement.setString(1, bird.getType());
            preparedStatement.setString(2, bird.getColor());
            preparedStatement.setDouble(3, bird.getWeight());
            preparedStatement.setInt(4, bird.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id)
    {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("delete from BIRDS where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
