package pliance.sdk.contracts;

import java.util.List;

public class RegisterPersonCommand {
	public String personReferenceId;
	public PersonIdentity identity;
	public String firstName;
	public String lastName;
	public String gender;
	public Birthdate birthdate;
	public List<Address> addresses;
	public RegisterPersonOptions options;
}
