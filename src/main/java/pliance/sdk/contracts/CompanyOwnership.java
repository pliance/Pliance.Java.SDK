package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class CompanyOwnership 
{
	public CompanyOwner[] companyOwners;
	public boolean hasForeignUltimateParent;
	public Company parentCompany;
	public PersonOwner[] personOwners;
	public Company ultimateParentCompany;
}

