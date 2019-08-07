package pliance.sdk.exceptions;

public class AggregatedException extends PlianceApiException {
	private static final long serialVersionUID = 7005659719037825036L;
	private Exception _exception;

	public AggregatedException(Exception exception) {
		super(exception.getMessage());

		_exception = exception;
	}

	public Exception getException() {
		return _exception;
	}
}