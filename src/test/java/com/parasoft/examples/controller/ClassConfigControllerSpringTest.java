package com.parasoft.examples.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

/**
 * Parasoft Jtest UTA: Test class for ClassConfigController
 *
 * @see com.parasoft.examples.controller.ClassConfigController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigClass.class)
@WebAppConfiguration
public class ClassConfigControllerSpringTest
{

    // Parasoft Jtest UTA: Component under test
    @Autowired
    ClassConfigController controller;

    // Parasoft Jtest UTA: Spring MVC test support class
    MockMvc mockMvc;

    // Parasoft Jtest UTA: Initialize Spring MVC test support class
    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * Parasoft Jtest UTA: Test for takesText(String, Model)
     *
     * @see com.parasoft.examples.controller.ClassConfigController#takesText(String, Model)
     */
    @Test
    public void testTakesText()
        throws Throwable
    {
        // When
        String payload = "payload"; // UTA: default value
        ResultActions actions = mockMvc.perform(put("/classconfig").content(payload));

        // Then
        actions.andExpect(status().isOk());
        // actions.andExpect(header().string("", ""));
        // actions.andExpect(view().name(""));
        // actions.andExpect(model().attribute("", ""));
    }
}
