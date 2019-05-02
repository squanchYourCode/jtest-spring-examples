/**
 *
 */

package com.parasoft.examples.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.powermock.modules.junit4.rule.PowerMockRule;

import com.parasoft.examples.model.IPet;

/**
 * Parasoft Jtest UTA: Test class for MultiService
 *
 * @see com.parasoft.examples.service.MultiService
 */
public class MultiServiceTest
{
    @Rule
    public PowerMockRule rule = new PowerMockRule();

    /**
     * Parasoft Jtest UTA: Test for findPet(String)
     *
     * @see com.parasoft.examples.service.MultiService#findPet(String)
     */
    @Test
    public void testFindPet()
        throws Throwable
    {
        // Given
        IPetService petService = mockIPetService();
        MultiService underTest = new MultiService(petService);

        // When
        String name = "Spot";
        IPet result = underTest.findPet(name);

        // Then
        assertNotNull(result);
    }

    /**
     * Parasoft Jtest UTA: Helper method to generate and configure mock of IPetService
     */
    private static IPetService mockIPetService()
        throws Throwable
    {
        IPetService petService = mock(IPetService.class);
        IPet findPetResult = mock(IPet.class);
        when(petService.findPet(anyString())).thenReturn(findPetResult);
        return petService;
    }
}
