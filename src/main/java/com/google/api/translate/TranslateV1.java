/**
 * Translate.java
 *
 * Copyright (C) 2007,  Richard Midwinter
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
package com.google.api.translate;

import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.tecnick.htmlutils.htmlentities.HTMLEntities;

/**
 * Makes the Google Translate API available to Java applications.
 * 
 * @author Richard Midwinter
 * @author Mike Nereson
 * @author Emeric Vernat
 * @author Juan B Cabral
 * @author Kramar Tomas
 */
@Deprecated
public final class TranslateV1 extends GoogleAPI implements Translate {
	
	/**
	 * Constants.
	 */
    private static final String
    		LANG_PARAM = "&langpair=",
    		TEXT_PARAM = "&q=",
    		PIPE_PARAM = "%7C",
    		URL = "http://ajax.googleapis.com/ajax/services/language/translate",
    		PARAMETERS = "v=2.0&langpair=#FROM#%7C#TO#&q=";

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(final String text, final Language from, final Language to) throws GoogleAPIException {
    	try {
	    	validateReferrer();
	    	
	    	final URL url = new URL(URL);
	    	final String parameters = PARAMETERS.replaceAll("#FROM#", from.toString()).replaceAll("#TO#", to.toString())
	    			+URLEncoder.encode(text, ENCODING) +(key != null ? "&key=" +key : "");
	    	
	    	final JSONObject json = retrieveJSON(url, parameters);
	    	
	    	return getJSONResponse(json);
    	} catch (final Exception e) {
    		throw new GoogleAPIException(e);
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(final String[] text, final Language from, final Language to) throws GoogleAPIException {
    	try {
	    	validateReferrer();
	    	
	    	final Language[] fromArgs = new Language[text.length];
	    	final Language[] toArgs = new Language[text.length];
	    	
	    	for (int i = 0; i<text.length; i++) {
	    		fromArgs[i] = from;
	    		toArgs[i] = to;
	    	}
	    	
	    	return execute(text, fromArgs, toArgs);
    	} catch (final Exception e) {
    		throw new GoogleAPIException(e);
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(final String text, final Language from, final Language[] to) throws GoogleAPIException {
    	try {
	    	validateReferrer();
	    	
	    	final String[] textArgs = new String[to.length];
	    	final Language[] fromArgs = new Language[to.length];
	    	
	    	for (int i = 0; i<to.length; i++) {
	    		textArgs[i] = text;
	    		fromArgs[i] = from;
	    	}
	    	
	    	return execute(textArgs, fromArgs, to);
    	} catch (final Exception e) {
    		throw new GoogleAPIException(e);
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(final String[] text, final Language from[], final Language[] to) throws GoogleAPIException {
    	try {
	    	validateReferrer();
	    	
	    	if (text.length != from.length || from.length != to.length) {
	    		throw new Exception(
	    				"[google-api-translate-java] The same number of texts, from and to languages must be supplied.");
	    	}
	    	
	    	if (text.length == 1) {
	    		return new String[] { execute(text[0], from[0], to[0]) };
	    	}
	    	
	    	final String[] responses = new String[text.length];
	    	
	    	final StringBuilder parametersBuilder = new StringBuilder();
	    	
	    	parametersBuilder.append(PARAMETERS.replaceAll("#FROM#", from[0].toString()).replaceAll("#TO#", to[0].toString())  +(key != null ? "&key=" +key : ""));
	    	parametersBuilder.append(URLEncoder.encode(text[0], ENCODING));
	    	
	    	for (int i = 1; i<text.length; i++) {
	    		parametersBuilder.append(LANG_PARAM);
	    		parametersBuilder.append(from[i].toString());
	    		parametersBuilder.append(PIPE_PARAM);
	    		parametersBuilder.append(to[i].toString());
	    		parametersBuilder.append(TEXT_PARAM);
	    		parametersBuilder.append(URLEncoder.encode(text[i].toString(), ENCODING));
	    	}
	    	
	    	final URL url = new URL(URL);
	    	
	    	final JSONArray json = retrieveJSON(url, parametersBuilder.toString()).getJSONArray("responseData");
	    	
	    	for (int i = 0; i<json.length(); i++) {
	    		final JSONObject obj = json.getJSONObject(i);
	    	
		    	responses[i] = getJSONResponse(obj);
	    	}
	    	
	    	return responses;
    	} catch (final Exception e) {
    		throw new GoogleAPIException(e);
    	}
    }
    
    /**
     * Returns the JSON response data as a String. Throws an exception if the status is not a 200 OK.
     * 
     * @param json The JSON object to retrieve the response data from.
     * @return The responseData from the JSONObject.
     * @throws Exception If the responseStatus is not 200 OK.
     */
    private static String getJSONResponse(final JSONObject json) throws Exception {
    	if (json.getString("responseStatus").equals("200")) {
    		final String translatedText = json.getJSONObject("responseData").getString("translatedText");
    		return HTMLEntities.unhtmlentities(translatedText);
    	} else {
    		throw new GoogleAPIException("Google returned the following error: [" +json.getString("responseStatus") +"] "
    				+json.getString("responseDetails"));
    	}
    }
}