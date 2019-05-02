package com.parasoft.examples.model;

/**
 * An example POJO which extends an Abstract class
 */
public class Cat
    extends AbstractPet
{

    public Cat(String name)
    {
        super(name);
    }

    @Override
    public void speak()
    {
        // meow
    }
}
