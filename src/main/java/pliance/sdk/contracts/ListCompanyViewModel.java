package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ListCompanyViewModel 
{
	public boolean active;
	public ListAddress[] addresses;
	public boolean isSanction;
	public boolean isSie;
	public String listId;
	public ListCompanyNameViewModel[] names;
	public String[] notes;
	public String[] sanctionLists;
	public WatchlistSource watchlistSource;
}

