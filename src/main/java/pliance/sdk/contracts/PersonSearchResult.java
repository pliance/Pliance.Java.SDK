package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class PersonSearchResult 
{
	public boolean archived;
	public TextMatch[] firstName;
	public PersonIdentity identity;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public TextMatch[] lastName;
	public String personReferenceId;
}

