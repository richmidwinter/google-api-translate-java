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