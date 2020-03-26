package pliance.sdk.contracts.models.company;

import pliance.sdk.contracts.models.CompanyIdentity;
import pliance.sdk.contracts.models.RegisterCompanyOptions;

public class RegisterCompanyCommand {
	public String companyReferenceId;
	public CompanyIdentity identity;
	public String name;
	public RegisterCompanyOptions options;
}
