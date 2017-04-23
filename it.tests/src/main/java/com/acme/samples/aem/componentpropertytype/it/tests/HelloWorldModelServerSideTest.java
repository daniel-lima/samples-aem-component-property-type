package com.acme.samples.aem.componentpropertytype.it.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.junit.annotations.SlingAnnotationsTestRunner;
import org.apache.sling.junit.annotations.TestReference;
import org.apache.sling.settings.SlingSettingsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.acme.samples.aem.componentpropertytype.core.models.HelloWorldModel;

/** 
 *  Test case which uses OSGi services injection
 *  to get hold of the HelloWorldModelServerSideTest which 
 *  it wants to test server-side. 
 */
@RunWith(SlingAnnotationsTestRunner.class)
public class HelloWorldModelServerSideTest {

    @TestReference
    private ResourceResolverFactory resourceResolverFactory;

    @TestReference
    private SlingSettingsService settings;
    
    private ResourceResolver resourceResolver;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() throws LoginException {
    	this.resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
    }
    
    @After
    public void tearDown() {
    	if (this.resourceResolver != null) {
    		this.resourceResolver.close();
    		this.resourceResolver = null;
    	}
    }
    
    @Test
    public void testHelloWorldModelServerSide() throws Exception {
    	Resource resource = this.resourceResolver.getResource("/content/samples-component-property-type");
    	HelloWorldModel hello = resource.adaptTo(HelloWorldModel.class);
    	
        assertNotNull(
                "Expecting HelloWorldModel to be injected by Sling test runner",
                hello);

        assertNotNull("Expecting the slingsettings to be injected by Sling test runner", settings);

        assertTrue("Expecting the HelloWorldModel to return the slingId as part of the message", 
                hello.getMessage().contains(settings.getSlingId()));
    }
}
