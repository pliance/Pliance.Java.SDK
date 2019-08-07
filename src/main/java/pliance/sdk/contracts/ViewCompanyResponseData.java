package pliance.sdk.contracts;
import java.util.List;

import pliance.sdk.contracts.graphs.*;

public class ViewCompanyResponseData {
	public String companyReferenceId;
	public CompanyIdentity identity;
	public String name;
	public Graph graph;
	public List<Beneficiary> beneficiaries;
}
