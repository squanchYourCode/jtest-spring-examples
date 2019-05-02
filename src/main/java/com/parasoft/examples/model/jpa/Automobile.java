package com.parasoft.examples.model.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * An example JPA Entity, managed by instances of IAutomobileDao
 */
@Entity
@Table(name = "AUTOMOBILES")
@Proxy(lazy = false)
public class Automobile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String make;

    private String model;

    private String color;

    private int mileage;

    public Automobile()
    {
        // Default constructor
    }

    public Automobile(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getMileage()
    {
        return mileage;
    }

    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("id=").append(id)
            .append(", make=").append(make)
            .append(", model=").append(model)
            .append(", color=").append(color)
            .append(", mileage=").append(mileage);
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Automobile) {
            Automobile other = (Automobile) obj;
            return id == other.id &&
                    make.equals(other.make) &&
                    model.equals(other.model) &&
                    color.equals(other.color) &&
                    mileage == other.mileage;
        }
        return false;
    }
}
