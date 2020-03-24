package pliance.sdk.contracts.models;
import java.util.List;

import pliance.sdk.contracts.models.graphs.*;

public class ViewCompanyResponseData {
	public String companyReferenceId;
	public CompanyIdentity identity;
	public String name;
	public Graph graph;
	public List<Beneficiary> beneficiaries;
	public boolean archived;
	public LastChanged lastChanged;
	public CompanyHit[][] hits;
}
