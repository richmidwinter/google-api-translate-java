/**
 * 
 */
package com.google.api.translate;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Richard Midwinter
 *
 */
public class LoopTest extends TestCase {
	@Test
	public void testTranslate() throws Exception {
		System.out.println("testTranslate");
		
		Translate.setHttpReferrer("http://code.google.com/p/google-api-translate-java/");
		
		for (int i = 0; i<30; i++) {
			System.out.println("Loop: " +i);
			assertEquals("مرحبا العالم", Translate.execute("Hello world", Language.ENGLISH, Language.ARABIC));
		}
	}
}