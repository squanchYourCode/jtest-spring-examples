package com.parasoft.examples.model.jpa;

import java.util.List;

/**
 * Defines operations for a DAO which manages Automobile entities
 */
public interface IAutomobileDao
{
    /**
     * Create a new Automobile by persisting it.
     */
    void create(Automobile automobile);

    /**
     * Find an Automobile by ID
     */
    Automobile get(long id);

    /**
     * Save changes to an already managed Automobile
     */
    void save(Automobile automobile);

    /**
     * Delete an existing Automobile. The Automobile must be managed.
     */
    void delete(long id);

    /**
     * Get all Automobiles in the system
     */
    List<Automobile> getAll();

    /**
     * Find all Automobiles by make.
     */
    List<Automobile> findByMake(String make);
}
