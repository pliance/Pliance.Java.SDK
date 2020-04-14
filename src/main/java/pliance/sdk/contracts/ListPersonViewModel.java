package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ListPersonViewModel 
{
	public ListAddress[] addresses;
	public ListBirthdate[] birthdates;
	public String[] countries;
	public Gender gender;
	public String[] images;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public String listId;
	public String[] lists;
	public ListPersonNameViewModel[] names;
	public String nationalIdentificationNumber;
	public String[] nationalities;
	public ListRelationViewModel[] relations;
	public ListRole[] roles;
}

