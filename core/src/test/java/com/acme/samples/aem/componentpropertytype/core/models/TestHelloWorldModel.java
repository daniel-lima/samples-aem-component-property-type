package com.acme.samples.aem.componentpropertytype.core.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import junitx.util.PrivateAccessor;

import org.apache.sling.settings.SlingSettingsService;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple JUnit test verifying the HelloWorldModel
 */
public class TestHelloWorldModel {

    //@Inject
    private HelloWorldModel hello;
    
    private String slingId;
    
    @Before
    public void setup() throws Exception {
        SlingSettingsService settings = mock(SlingSettingsService.class);
        slingId = UUID.randomUUID().toString();
        when(settings.getSlingId()).thenReturn(slingId);

        hello = new HelloWorldModel();
        PrivateAccessor.setField(hello, "settings", settings);
        hello.init();
    }
    
    @Test
    public void testGetMessage() throws Exception {
        // some very basic junit tests
        String msg = hello.getMessage();
        assertNotNull(msg);
        assertTrue(msg.length() > 0);
    }

}
