package pliance.sdk.contracts;

import java.util.Date;

import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class RegisterCompanyCommand 
{
	public String companyReferenceId;
	public CompanyIdentity identity;
	public String name;
	public RegisterCompanyOptions options;
}

