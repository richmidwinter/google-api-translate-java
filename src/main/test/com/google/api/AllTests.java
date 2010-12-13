/**
 * AllTests.java
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
package com.google.api;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.api.detect.DetectTest;
import com.google.api.translate.TranslateTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test");
		suite.addTestSuite(TranslateTest.class);
		suite.addTestSuite(DetectTest.class);
		return suite;
	}
}