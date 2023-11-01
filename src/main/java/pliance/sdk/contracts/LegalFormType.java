package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;
import com.google.gson.annotations.SerializedName;

public enum LegalFormType
{
	@SerializedName("LimitedCompany") LimitedCompany,
	@SerializedName("PrivateBusinessGovControlled") PrivateBusinessGovControlled,
	@SerializedName("ForeignCompany") ForeignCompany,
	@SerializedName("Bank") Bank,
	@SerializedName("SoleProprietorship") SoleProprietorship,
	@SerializedName("GeneralPartnership") GeneralPartnership,
	@SerializedName("Society") Society,
	@SerializedName("Foundation") Foundation,
	@SerializedName("HousingCompany") HousingCompany,
	@SerializedName("StateOrCountyCompany") StateOrCountyCompany,
	@SerializedName("ReligiousOrganisation") ReligiousOrganisation,
	@SerializedName("InsuranceCompany") InsuranceCompany,
	@SerializedName("Collaborations") Collaborations,
	@SerializedName("Other") Other,
}

