package pliance.sdk.exceptions;

public class PlianceApiException extends Exception {
	private static final long serialVersionUID = -5591485877451951631L;

	public PlianceApiException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public PlianceApiException(String message) {
		super(message);
	}	
}
