package pliance.sdk.contracts;

import java.util.Date;

import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ViewPersonResponseData 
{
	public Address[] addresses;
	public boolean archived;
	public Birthdate birth;
	public String birthdate;
	public EngagementModel[] engagements;
	public String firstName;
	public Gender gender;
	public boolean highRiskCountry;
	public PersonHit[][] hits;
	public PersonIdentity identity;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public LastChanged lastChanged;
	public String lastName;
	public String personReferenceId;
}

