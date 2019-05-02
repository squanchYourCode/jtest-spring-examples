package com.parasoft.examples.model;

public class Alien
{
    private Planet planet;

    private Person person;

    public Alien(Person person, Planet planet)
    {
        this.planet = planet;
        this.person = person;
    }

    public static String speak()
    {
        return "Blork";
    }

    public Planet getPlanet()
    {
        return planet;
    }

    public Person getPerson()
    {
        return person;
    }
}
