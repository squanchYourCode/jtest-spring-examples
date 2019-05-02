package com.parasoft.examples.model;

/**
 * An example POJO which extends an Abstract class
 */
public class Dog
    extends AbstractPet
{

    public Dog(String name)
    {
        super(name);
    }

    @Override
    public void speak()
    {
        // woof
    }
}
