package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ViewCompanyResponseData 
{
	public boolean archived;
	public ViewCompanyPersonResponse[] beneficiaries;
	public String[] checkedWatchLists;
	public String companyReferenceId;
	public boolean highRiskCountry;
	public CompanyHit[][] hits;
	public CompanyIdentity identity;
	public boolean isSanction;
	public boolean isSie;
	public LastChanged lastChanged;
	public String name;
}

