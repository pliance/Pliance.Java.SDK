package pliance.sdk.contracts.person;

import pliance.sdk.contracts.models.Filter;
import pliance.sdk.contracts.models.Page;

public class PersonSearchQuery {
	public Page page;
	public Filter filter;
	public String query = "";
}