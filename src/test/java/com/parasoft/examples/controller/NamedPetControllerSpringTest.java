package com.parasoft.examples.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.parasoft.examples.service.PetService;

/**
 * An example JUnit test for NamedPetController, for demonstrating the Instantiation and mocking actions.<br/>
 * Note: This test is expected to fail until either a mock or real Bean is returned for PetService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class NamedPetControllerSpringTest
{

    // Component under test
    @Autowired
    NamedPetController controller;

    // Parasoft Jtest UTA: Spring MVC test support class
    MockMvc mockMvc;

    // Parasoft Jtest UTA: Initialize Spring MVC test support class
    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // Parasoft Jtest UTA: Configure test dependencies using Java-based configuration
    @Configuration
    static class Config
    {
        /**
         * Parasoft Jtest UTA: Component under test
         *
         * @see com.parasoft.examples.controller.NamedPetController
         */
        @Bean(name = "NamedPet")
        public NamedPetController getNamedPetController()
        {
            return new NamedPetController();
        }

        /**
         * Parasoft Jtest UTA: Dependency generated for autowired field "petService" in NamedPetController
         *
         * @see com.parasoft.examples.controller.NamedPetController#petService
         */
        @Bean(name = "PetService")
        public PetService getPetService()
        {
            return null;
        }
    }

    /**
     * Parasoft Jtest UTA: Test for getByName(String)
     *
     * @see com.parasoft.examples.controller.NamedPetController#getByName(String)
     */
    @Test
    public void testGetByName()
        throws Exception
    {
        // When
        String name = "Spot";
        ResultActions actions = mockMvc.perform(get("/pets/" + name));

        // Then
        actions.andExpect(status().isOk());
    }

    /**
     * Parasoft Jtest UTA: Test for getByType(String)
     *
     * @see com.parasoft.examples.controller.NamedPetController#getByType(String)
     */
    @Test
    public void testGetByType()
        throws Exception
    {
        // When
        String type = "cat";
        ResultActions actions = mockMvc.perform(get("/pets?type=" + type));

        // Then
        actions.andExpect(status().isOk());
    }
}
