package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class LinkModel 
{
	public Birthdate birthDate;
	public EntityType entityType;
	public String firstName;
	public Gender gender;
	public String id;
	public String lastName;
	public LinkDescriptionModel[] linkDescriptions;
	public PersonDetailsHitModel[] matches;
	public String name;
	public String organizationIdentityNumber;
	public String personIdentityNumber;
}

