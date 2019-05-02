package com.parasoft.examples.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * An example Junit test for XmlConfigController. This test uses an XML configuration
 * instead of Java configuration. Adding a test to this class does not create the
 * Config inner class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/**/testContext.xml"})
public class XmlConfigControllerSpringTest
{

    // Component under test
    @Autowired
    XmlConfigController controller;

    // Parasoft Jtest UTA: Spring MVC test support class
    MockMvc mockMvc;

    // Parasoft Jtest UTA: Initialize Spring MVC test support class
    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
}
