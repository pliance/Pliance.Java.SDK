package pliance.sdk.contracts.models.company;

import java.util.List;

import pliance.sdk.contracts.models.CompanyIdentity;
import pliance.sdk.contracts.models.TextMatch;

public class CompanySearchResult {
	public String companyReferenceId;
	public List<TextMatch> name;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public CompanyIdentity identity;
}
