package Contracts;

import java.util.List;

public class ViewPersonResponseData {
	public String PersonReferenceId;
	public PersonIdentity Identity;
	public String FirstName;
	public String LastName;
	public String Birthdate;
	public List<Address> Addresses;
	public List<List<Hit>> Hits;
	public Gender Gender;
}
