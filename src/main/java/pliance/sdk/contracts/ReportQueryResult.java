package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class ReportQueryResult extends Response 
{
	public String[] highRiskCountries;
	public PersonReport[] personReports;
}

