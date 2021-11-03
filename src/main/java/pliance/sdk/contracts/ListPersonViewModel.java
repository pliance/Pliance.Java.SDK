package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ListPersonViewModel 
{
	public boolean active;
	public ListAddress[] addresses;
	public ListBirthdate[] birthdates;
	public String[] countries;
	public boolean deceased;
	public Gender gender;
	public String[] images;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public boolean isSip;
	public String listId;
	public String[] lists;
	public ListPersonNameViewModel[] names;
	public String nationalIdentificationNumber;
	public String[] nationalities;
	public String[] notes;
	public ListRelationViewModel[] relations;
	public ListRole[] roles;
	public String[] sources;
	public WatchlistSource watchlistSource;
}

