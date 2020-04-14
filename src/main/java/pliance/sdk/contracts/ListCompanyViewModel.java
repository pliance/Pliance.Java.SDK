package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ListCompanyViewModel 
{
	public String companyReferenceId;
	public boolean isSanction;
	public ListCompanyNameViewModel[] names;
	public String[] sanctionLists;
}

