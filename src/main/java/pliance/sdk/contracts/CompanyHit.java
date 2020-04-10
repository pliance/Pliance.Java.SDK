package pliance.sdk.contracts;

import java.util.Date;

import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class CompanyHit 
{
	public String aliasId;
	public ClassificationType classification;
	public boolean isSanction;
	public TextMatch[] matchedName;
	public String matchId;
	public String name;
}

