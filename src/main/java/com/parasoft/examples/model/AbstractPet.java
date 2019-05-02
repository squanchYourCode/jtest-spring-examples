package com.parasoft.examples.model;

/**
 * An example abstract class which other POJOs can extend
 */
public abstract class AbstractPet
    implements IPet
{
    private String name;

    protected AbstractPet(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }
}
