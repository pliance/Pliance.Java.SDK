package pliance.sdk.Contracts;

import java.util.List;

public class CompanySearchResult {
	public String CompanyReferenceId;
	public List<TextMatch> Name;
	public boolean IsPep;
	public boolean IsRca;
	public boolean IsSanction;
	public CompanyIdentity Identity;
}
