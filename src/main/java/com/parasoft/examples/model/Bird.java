package com.parasoft.examples.model;

/**
 * An example POJO for JDBC
 */
public class Bird
{

    private int id;

    private String type;

    private String color;

    private double weight;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

}
