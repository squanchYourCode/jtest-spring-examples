package com.parasoft.examples.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import com.parasoft.examples.model.Person;
import com.parasoft.examples.service.PersonService;

/**
 * An example Junit test for PeopleController with populated values and assertions
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PeopleControllerSpringTest
{

    // Component under test
    @Autowired
    PeopleController controller;

    @Autowired
    PersonService personService;

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
         * @see com.parasoft.examples.controller.PeopleController
         */
        @Bean
        public PeopleController getPersonController()
        {
            return new PeopleController();
        }

        /**
         * Parasoft Jtest UTA: Dependency generated for autowired field "personService" in AbstractPeopleController
         *
         * @see com.parasoft.examples.controller.AbstractPeopleController#personService
         */
        @Bean
        public PersonService getPersonService()
        {
            return mock(PersonService.class);
        }
    }

    /**
     * Parasoft Jtest UTA: Test for getPerson(int)
     *
     * @see com.parasoft.examples.controller.PeopleController#getPerson(int)
     */
    @Test
    public void testGetPerson()
        throws Exception
    {
        // When
        when(personService.getPerson(1)).thenReturn(new Person("John", 32));
        int id = 1;
        ResultActions actions = mockMvc.perform(get("/people/" + id));

        // Then
        actions.andExpect(status().isOk());
        actions.andExpect(content().string("{\"name\":\"John\",\"age\":32}"));
    }

    /**
     * Parasoft Jtest UTA: Test for people(Model)
     *
     * @see com.parasoft.examples.controller.PeopleController#people(Model)
     */
    @Test
    public void testPeople()
        throws Exception
    {
        // When
        ResultActions actions = mockMvc.perform(get("/people"));

        // Then
        actions.andExpect(status().isOk());
        actions.andExpect(view().name("people.jsp"));
    }
}
