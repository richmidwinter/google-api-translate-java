/**
 * DetectResult.java
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

import com.google.api.translate.Language;

/**
 * Represents the result of a Detect query.
 * 
 * @author Richard Midwinter
 * @author Soren AD <soren@tanesha.net>
 */
public class DetectResult {

	private Language language;
	private boolean reliable;
	private double confidence;
	
	public DetectResult(final Language language, final boolean reliable, final double confidence) {
		this.language = language;
		this.reliable = reliable;
		this.confidence = confidence;
	}
	
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public boolean isReliable() {
		return reliable;
	}
	public void setReliable(boolean reliable) {
		this.reliable = reliable;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
}