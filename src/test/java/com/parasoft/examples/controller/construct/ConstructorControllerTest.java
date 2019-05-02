package com.parasoft.examples.controller.construct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ConstructorControllerTest
{

    // Component under test
    @Autowired
    ConstructorController controller;

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
         * @see com.parasoft.examples.controller.construct.ConstructorController
         */
        @Bean
        public ConstructorController getConstructorController()
        {
            String val = ""; // UTA: default value
            return new ConstructorController(val);
        }
    }

    /**
     * Parasoft Jtest UTA: Test for handler(Model)
     *
     * @see com.parasoft.examples.controller.construct.ConstructorController#handler(Model)
     */
    @Test
    public void testHandler()
        throws Exception
    {
        // When
        ResultActions actions = mockMvc.perform(get("/constructor"));

        // Then
        actions.andExpect(status().isOk());
        actions.andExpect(view().name("constructor.jsp"));
    }
}
