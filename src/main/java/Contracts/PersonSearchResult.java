package Contracts;

import java.util.List;

public class PersonSearchResult {
	public String PersonReferenceId;
	public List<TextMatch> FirstName;
	public List<TextMatch> LastName;
	public boolean IsPep;
	public boolean IsRca;
	public boolean IsSanction;
	public PersonIdentity Identity;
}
