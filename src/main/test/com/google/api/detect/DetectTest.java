package com.google.api.detect;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.api.GoogleAPI;
import com.google.api.detect.Detect;
import com.google.api.detect.DetectResult;
import com.google.api.translate.Language;

/**
 * @author Richard Midwinter
 */
public class DetectTest extends TestCase {
	@Test
	public void testDetect() throws Exception {
		System.out.println("testDetect");
		
		GoogleAPI.setHttpReferrer("http://code.google.com/p/google-api-translate-java/");
		
		DetectResult detectResult = Detect.execute("Hello world");
		
		assertEquals(Language.ENGLISH, detectResult.getLanguage());
		assertTrue(detectResult.getConfidence() > 0);
	}
}
