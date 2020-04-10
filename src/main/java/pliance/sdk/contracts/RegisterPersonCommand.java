package pliance.sdk.contracts;

import java.util.Date;

import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class RegisterPersonCommand 
{
	public Address[] addresses;
	public Birthdate birthdate;
	public String firstName;
	public String gender;
	public PersonIdentity identity;
	public String lastName;
	public RegisterPersonOptions options;
	public String personReferenceId;
}

