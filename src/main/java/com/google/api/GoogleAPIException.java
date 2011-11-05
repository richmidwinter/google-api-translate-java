/**
 * 
 */
package com.google.api;

/**
 * @author Richard Midwinter
 */
public class GoogleAPIException extends Exception {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1904924954995479356L;
	
	public GoogleAPIException(final String message) {
		super(message);
	}

	public GoogleAPIException(final Exception e) {
		super(e);
	}
}
