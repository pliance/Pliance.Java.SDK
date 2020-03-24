package pliance.sdk.contracts.models;

import java.util.List;

public class PersonSearchResult {
	public String personReferenceId;
	public List<TextMatch> firstName;
	public List<TextMatch> lastName;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public PersonIdentity identity;
}
