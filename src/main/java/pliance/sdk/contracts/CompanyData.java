package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class CompanyData 
{
	public String address;
	public BoardMember[] boardmembers;
	public String city;
	public String country;
	public String description;
	public LegalForm legalForm;
	public String name;
	public Owners owners;
	public Company parentCompany;
	public Date registrationDate;
	public String signatory;
	public UltimateCompany ultimateParentCompany;
	public String zipCode;
}

