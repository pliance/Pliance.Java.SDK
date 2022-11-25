package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class PersonDetailsHitModel 
{
	public String aliasId;
	public ClassificationType classification;
	public String firstName;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public boolean isSip;
	public String lastName;
	public TextMatch[] matchedFirstName;
	public TextMatch[] matchedLastName;
	public String matchId;
	public String referenceId;
	public double score;
}

