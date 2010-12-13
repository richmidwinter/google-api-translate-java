/**
 * Detect.java
 *
 * Copyright (C) 2009,  Richard Midwinter
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

import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.google.api.GoogleAPI;
import com.google.api.translate.Language;

/**
 * Makes the Google Detect API available to Java applications.
 * 
 * @author Richard Midwinter
 * @author Soren AD <soren@tanesha.net>
 */
public class Detect extends GoogleAPI {
	
	/**
	 * Constants.
	 */
	private static String URL = "http://ajax.googleapis.com/ajax/services/language/detect?v=1.0&q=";

	/**
	 * Detects the language of a supplied String.
	 * 
	 * @param text The String to detect the language of.
	 * @return A DetectResult object containing the language, confidence and reliability.
	 * @throws Exception on error.
	 */
	public static DetectResult execute(final String text) throws Exception {
    	validateReferrer();
    	
		final URL url = new URL(URL +URLEncoder.encode(text, ENCODING));
		
		final JSONObject json = retrieveJSON(url);
		
		return new DetectResult(
				Language.fromString(json.getJSONObject("responseData").getString("language")),
				json.getJSONObject("responseData").getBoolean("isReliable"),
				json.getJSONObject("responseData").getDouble("confidence"));
	}
}
