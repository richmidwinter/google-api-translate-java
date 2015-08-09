/**
 * DetectTest.java
 *
 * Copyright (C) 2010,  Richard Midwinter
 *
 * This file is part of google-api-translate-java.
 *
 * google-api-translate-java is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * google-api-translate-java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with google-api-translate-java. If not, see <http://www.gnu.org/licenses/>.
 */
package com.google.api.detect;

import com.google.api.Files;
import junit.framework.TestCase;

import org.junit.Test;

import com.google.api.GoogleAPI;
import com.google.api.detect.Detect;
import com.google.api.detect.DetectResult;
import com.google.api.translate.Language;

import java.io.File;

/**
 * @author Richard Midwinter
 */
public class DetectTest extends TestCase {
	@Test
	public void testDetect() throws Exception {
		System.out.println("testDetect");

        GoogleAPI.setHttpReferrer(Files.read(new File(System.getProperty("user.home") + "/.google-translate-api-referrer.txt")).trim());
        GoogleAPI.setKey(Files.read(new File(System.getProperty("user.home") + "/.google-translate-api.key")).trim());
		
		DetectResult detectResult = Detect.execute("Hello world");
		
		assertEquals(Language.ENGLISH, detectResult.getLanguage());
		assertTrue(detectResult.getConfidence() > 0);
	}
}
