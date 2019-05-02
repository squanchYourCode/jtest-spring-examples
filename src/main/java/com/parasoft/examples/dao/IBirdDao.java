package com.parasoft.examples.dao;

import com.parasoft.examples.model.Bird;

/**
 * An example POJO for Spring JDBC examples
 */
public interface IBirdDao
{
    Bird findById(int id);

    /**
     * @param bird
     * @return id of the newest inserted Bird object
     */
    int create(Bird bird);

    void update(Bird bird);

    void delete(int id);
}
