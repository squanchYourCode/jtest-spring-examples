package com.parasoft.examples.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parasoft.examples.dao.IBirdDao;
import com.parasoft.examples.model.Bird;

/**
 * An example Service which manages Bird objects
 */
@Service
@Transactional
public class BirdService
{
    private IBirdDao birdDao;

    @Autowired
    public void setPersonDao(IBirdDao birdDao)
    {
        this.birdDao = birdDao;
    }

    public Bird findById(int id)
    {
        return birdDao.findById(id);
    }

    public int create(Bird bird)
    {
        return birdDao.create(bird);
    }

    public void update(Bird bird)
    {
        birdDao.update(bird);
    }

    public void delete(int id)
    {
        birdDao.delete(id);
    }
}
