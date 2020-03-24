package pliance.sdk.contracts.models;

import java.util.List;

public class ListCompanyViewModel {
	public String companyReferenceId;
	public boolean isSanction;
	public List<ListCompanyNameViewModel> names;
	public List<String> sanctionLists;
}
