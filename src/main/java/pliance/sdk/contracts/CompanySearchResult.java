package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class CompanySearchResult 
{
	public boolean archived;
	public String companyReferenceId;
	public CompanyIdentity identity;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public TextMatch[] name;
}

