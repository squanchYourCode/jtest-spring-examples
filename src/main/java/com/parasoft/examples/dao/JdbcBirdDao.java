package com.parasoft.examples.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parasoft.examples.model.Bird;

/**
 * A simple implementation of IBirdDao which connects to a database through JdbcTemplate
 */
@Repository
public class JdbcBirdDao
    implements IBirdDao
{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Bird findById(int id)
    {
        return jdbcTemplate.queryForObject("select type, color, weight, id from BIRDS where id = ?", new RowMapper<Bird>()
        {

            @Override
            public Bird mapRow(ResultSet rs, int rowNum)
                throws SQLException
            {
                Bird bird = new Bird();
                bird.setId(rs.getInt("id"));
                bird.setType(rs.getString("type"));
                bird.setColor(rs.getString("color"));
                bird.setWeight(rs.getDouble("weight"));
                return bird;
            }
        }, id);
    }

    @Override
    public int create(Bird bird)
    {
        return jdbcTemplate.update("insert into BIRDS(type, color, weight) values(?,?,?); call identity()", bird.getType(), bird.getColor(),
            bird.getWeight());
    }

    @Override
    public void update(Bird bird)
    {
        jdbcTemplate.update("update BIRDS set type = ?, color = ?, weight = ? where id = ?", bird.getType(), bird.getColor(), bird.getWeight(),
            bird.getId());
    }

    @Override
    public void delete(int id)
    {
        jdbcTemplate.update("delete from BIRDS where id = ?", id);
    }
}
