/**
 * Files.java
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
package com.google.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files {

	private Files() {
	}
	
	/**
	 * Writes a String to a given file.
	 * 
	 * @param file The file to write to.
	 * @param content The text to write to the given file.
	 * @throws IOException Thrown on IO errors.
	 */
	public static void write(final File file, final String content) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(content);
		bw.close();
	}
	
	/**
	 * Reads a file to a String.
	 * @param file The file to read from.
	 * @return The content of the file as a String.
	 * @throws IOException Thrown on IO errors.
	 */
	public static String read(final File file) throws IOException {
		final StringBuilder sb = new StringBuilder();
		String line;

		final BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append('\n');
		}
		
		return sb.toString();
	}
}