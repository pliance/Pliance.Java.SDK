package pliance.sdk.Contracts;

public class Response {
	public ResponseStatus status;
	public boolean success; // => Status == ResponseStatus.Success
	public String message;
	public String stackTrace;
	public String checkpoint;
}
