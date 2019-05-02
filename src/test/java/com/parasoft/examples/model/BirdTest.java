package com.parasoft.examples.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BirdTest
{
    @Test
    public void testSerialization()
        throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        Bird bird = new Bird();
        bird.setId(1);
        bird.setColor("White");
        bird.setType("Dove");
        bird.setWeight(4.2d);
        assertEquals("{\"id\":1,\"type\":\"Dove\",\"color\":\"White\",\"weight\":4.2}", mapper.writeValueAsString(bird));
    }
}
