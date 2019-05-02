package com.parasoft.examples.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.parasoft.examples.model.Person;
import com.parasoft.examples.service.PersonService;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * An example parameterized Junit test for PeopleController, showing the result
 * of mocking methods on the PersonService. Example assertions are included and
 * populated.
 */
@ContextConfiguration
@RunWith(JUnitParamsRunner.class)
public class PeopleControllerSpringParameterizedTest
{

    // Parasoft Jtest UTA: Initialize Spring MVC test support class
    @Before
    public void setUpContext()
        throws Exception
    {
        new TestContextManager(getClass()).prepareTestInstance(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // Component under test
    @Autowired
    PeopleController controller;

    @Autowired
    PersonService personService;

    // Parasoft Jtest UTA: Spring MVC test support class
    MockMvc mockMvc;

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
        public PeopleController getPeopleController()
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
     * Parasoft Jtest UTA: Test for search(String, HttpSession, Locale)
     *
     * @see com.parasoft.examples.controller.PeopleController#search(String, HttpSession, Locale)
     */
    @Test
    @Parameters(method = "testSearch_Parameters")
    public void testSearch(String name)
        throws Exception
    {
        when(personService.findPerson("John")).thenReturn(new Person("John", 32));

        ResultActions actions = mockMvc.perform(get("/people/search/" + name));

        actions.andExpect(status().isOk());
        actions.andExpect(content().string("{\"name\":\"John\",\"age\":32}"));
    }

    // Parasoft Jtest UTA: Initialize test parameters
    @SuppressWarnings("unused")
    private static Object[] testSearch_Parameters()
    {
        // Parameters: name={0}
        return new Object[] { new Object[] { "John"},};
    }
}
