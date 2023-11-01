package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ViewCompanyV2ResponseData 
{
	public String address;
	public boolean archived;
	public String city;
	public String companyReferenceId;
	public String description;
	public boolean highRiskCountry;
	public CompanyHit[][] hits;
	public CompanyIdentity identity;
	public boolean isSanction;
	public boolean isSie;
	public LastChanged lastChanged;
	public LegalFormType legalForm;
	public LinkModel[] links;
	public String name;
	public String signatory;
	public Sni sni;
	public String zipcode;
}

