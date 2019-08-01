package Contracts;

public class Response {
	public ResponseStatus Status;
	public boolean Success; // => Status == ResponseStatus.Success
	public String Message;
	public String StackTrace;
	public String Checkpoint;
}
