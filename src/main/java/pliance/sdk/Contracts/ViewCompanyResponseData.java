package pliance.sdk.Contracts;
import pliance.sdk.Contracts.Graphs.*;

import java.util.List;

public class ViewCompanyResponseData {
	public String CompanyReferenceId;
	public CompanyIdentity Identity;
	public String Name;
	public Graph Graph;
	public List<Beneficiary> Beneficiaries;
}
