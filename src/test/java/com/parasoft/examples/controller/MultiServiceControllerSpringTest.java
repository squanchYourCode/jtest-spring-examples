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

import com.parasoft.examples.service.MultiService;

/**
 * An example JUnit test for MultiServiceController, for demonstrating the Instantiate or Mock it actions.<br/>
 * Note: This test is expected to fail until either a mock or real Bean is returned for MultiService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MultiServiceControllerSpringTest
{

    // Component under test
    @Autowired
    MultiServiceController controller;

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
         * @see com.parasoft.examples.controller.MultiServiceController
         */
        @Bean
        public MultiServiceController getMultiServiceController()
        {
            return new MultiServiceController();
        }

        /**
         * Parasoft Jtest UTA: Dependency generated for autowired field "service" in MultiServiceController
         *
         * @see com.parasoft.examples.controller.MultiServiceController#service
         */
        @Bean
        public MultiService getMultiService()
        {
            return null;
        }
    }

    /**
     * Parasoft Jtest UTA: Test for getPersonByName(String)
     *
     * @see com.parasoft.examples.controller.MultiServiceController#getPersonByName(String)
     */
    @Test
    public void testGetPersonByName()
        throws Exception
    {
        // When
        String name = "John";
        ResultActions actions = mockMvc.perform(get("/multi/person/" + name));

        // Then
        actions.andExpect(status().isOk());
        // actions.andExpect(header().string("", ""));
        // String response = ""; // UTA: Configure the expected response value
        // actions.andExpect(content().string(response));
    }
}
