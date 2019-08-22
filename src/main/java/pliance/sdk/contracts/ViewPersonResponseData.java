package pliance.sdk.contracts;

import java.util.List;

public class ViewPersonResponseData {
	public String personReferenceId;
	public PersonIdentity identity;
	public String firstName;
	public String lastName;
	public String birthdate;
	public List<Address> addresses;
	public Hit[][] hits;
	public Gender gender;
}
