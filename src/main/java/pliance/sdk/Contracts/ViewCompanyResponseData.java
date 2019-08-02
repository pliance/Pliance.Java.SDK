package pliance.sdk.Contracts;
import pliance.sdk.Contracts.Graphs.*;

import java.util.List;

public class ViewCompanyResponseData {
	public String companyReferenceId;
	public CompanyIdentity identity;
	public String name;
	public Graph graph;
	public List<Beneficiary> beneficiaries;
}
