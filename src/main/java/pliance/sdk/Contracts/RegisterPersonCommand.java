package pliance.sdk.Contracts;

import java.util.List;

public class RegisterPersonCommand {
	public String PersonReferenceId;
	public PersonIdentity Identity;
	public String FirstName;
	public String LastName;
	public String Gender;
	public Birthdate Birthdate;
	public List<Address> Addresses;
	public RegisterPersonOptions Options;
}
