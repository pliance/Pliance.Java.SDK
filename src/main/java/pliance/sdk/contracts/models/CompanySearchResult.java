package pliance.sdk.contracts.models;

import java.util.List;

public class CompanySearchResult {
	public String companyReferenceId;
	public List<TextMatch> name;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public CompanyIdentity identity;
}
