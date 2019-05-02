package com.parasoft.examples.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.parasoft.examples.model.jpa.Automobile;
import com.parasoft.examples.model.jpa.HsqlDbUtil;
import com.parasoft.examples.model.jpa.StaticAutomobileDao;

/**
 * Parasoft Jtest UTA: Test class for AutomobileController
 *
 * @see com.parasoft.examples.controller.AutomobileController
 * @author bmcglau
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(StaticAutomobileDao.class)
@ContextConfiguration
@WebAppConfiguration
public class AutomobileControllerSpringTest
{

    // Parasoft Jtest UTA: Component under test
    @Autowired
    AutomobileController controller;

    // Parasoft Jtest UTA: Spring MVC test support class
    MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    // Parasoft Jtest UTA: Initialize Spring MVC test support class
    @Before
    public void setup()
        throws Exception
    {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        HsqlDbUtil.initialize(HsqlDbUtil.getConnection());
    }

    // Parasoft Jtest UTA: Configure test dependencies using Java-based configuration
    @Configuration
    static class Config
    {
        /**
         * Parasoft Jtest UTA: Component under test
         *
         * @see com.parasoft.examples.controller.AutomobileController
         */
        @Bean
        public AutomobileController getAutomobileController()
        {
            return new AutomobileController();
        }

        @Bean
        public EntityManager getEntityManager()
        {
            return mock(EntityManager.class);
        }
    }

    /**
     * Parasoft Jtest UTA: Test for getAutomobile(int)
     *
     * @see com.parasoft.examples.controller.AutomobileController#getAutomobile(int)
     * @author bmcglau
     */
    @Test
    public void testGetAutomobile()
        throws Throwable
    {

        // When
        PowerMockito.mockStatic(StaticAutomobileDao.class);
        PowerMockito.when(StaticAutomobileDao.entityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Automobile.class, 1L)).thenReturn(new Automobile(1));
        long id = 1;
        ResultActions actions = mockMvc.perform(get("/automobiles/" + id));

        // Then
        actions.andExpect(status().isOk());
        // actions.andExpect(header().string("", ""));
        String response = "{\"id\":1,\"make\":null,\"model\":null,\"color\":null,\"mileage\":0}";
        actions.andExpect(content().string(response));
    }
}
