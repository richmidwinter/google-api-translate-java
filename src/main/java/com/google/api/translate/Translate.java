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

import com.google.api.GoogleAPIException;

/**
 * @author Richard Midwinter
 */
public interface Translate {
	
	/**
	 * Default instance of the Translate API.
	 */
	Translate DEFAULT = new TranslateV2();

    /**
     * Translates text from a given Language to another given Language using Google Translate.
     * 
     * @param text The String to translate.
     * @param from The language code to translate from.
     * @param to The language code to translate to.
     * @return The translated String.
     * @throws GoogleAPIException on error.
     */
	String execute(String text, Language from, Language to) throws GoogleAPIException;
	
    /**
     * Translates an array of text Strings from a given Language to another given Language using Google Translate.
     * 
     * @param text The array of Strings to translate.
     * @param from The language code to translate from.
     * @param to The language code to translate to.
     * @return The translated array of String results.
     * @throws GoogleAPIException on error.
     */
	String[] execute(String[] text, Language from, Language to) throws GoogleAPIException;
	
    /**
     * Translates a String from a given Language to an Array of Languages using Google Translate.
     * 
     * @param text The String to translate.
     * @param from The language code to translate from.
     * @param to The array of Languages to translate to.
     * @return The translated array of String results.
     * @throws GoogleAPIException on error.
     */
	String[] execute(String text, Language from, Language[] to) throws GoogleAPIException;
	
    /**
     * Translates text from a given Language to another given Language using Google Translate.
     * 
     * @param text The array of Strings to translate.
     * @param from The array of Language codes to translate from.
     * @param to The array of Language codes to translate to.
     * @return The translated array of String results.
     * @throws GoogleAPIException on error.
     */
	String[] execute(String[] text, Language from[], Language[] to) throws GoogleAPIException;
}
