package pliance.sdk.exceptions;

public class HttpException extends PlianceApiException {
	private static final long serialVersionUID = 4015061051307026266L;

	public HttpException(String message) {
		super(message);
	}
}