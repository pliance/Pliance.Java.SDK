package pliance.sdk.exceptions;

public class ArgumentNullException extends PlianceApiException {
	private static final long serialVersionUID = 1498707293688146875L;

	public ArgumentNullException(String message) {
		super(message);
	}
}