package ca.uhn.fhir.rest.param;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import ca.uhn.fhir.util.TestUtil;

public class ReferenceParamTest {

	@Test
	public void testWithResourceTypeAsQualifier() {
		
		ReferenceParam rp = new ReferenceParam();
		rp.setValueAsQueryToken(":Location", "123");
		assertEquals("Location", rp.getResourceType());
		assertEquals("123", rp.getIdPart());
		
	}

	@Test
	public void testWithResourceType() {
		
		ReferenceParam rp = new ReferenceParam();
		rp.setValueAsQueryToken(null, "Location/123");
		assertEquals("Location", rp.getResourceType());
		assertEquals("123", rp.getIdPart());
		
	}
	

	@AfterClass
	public static void afterClassClearContext() {
		TestUtil.clearAllStaticFieldsForUnitTest();
	}

}
