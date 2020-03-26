package pliance.sdk.contracts.models.person;

import java.util.List;

import pliance.sdk.contracts.models.Address;
import pliance.sdk.contracts.models.Birthdate;
import pliance.sdk.contracts.models.PersonIdentity;
import pliance.sdk.contracts.models.RegisterPersonOptions;

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
