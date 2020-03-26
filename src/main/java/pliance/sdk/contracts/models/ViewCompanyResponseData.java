package pliance.sdk.contracts.models;
import java.util.List;

public class ViewCompanyResponseData {
	public String companyReferenceId;
	public CompanyIdentity identity;
	public String name;
	public List<ViewPersonResponseData> beneficiaries;
	public boolean archived;
	public boolean highRiskCountry;
	public CompanyHit[][] hits;
	public LastChanged lastChanged;
	public boolean isSanction;
}
