package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;
import com.google.gson.annotations.SerializedName;

public enum WebhookPokeType
{
	@SerializedName("PersonSanctionMatched") PersonSanctionMatched,
	@SerializedName("PersonSanctionMatchRemoved") PersonSanctionMatchRemoved,
	@SerializedName("CompanySanctionMatched") CompanySanctionMatched,
	@SerializedName("CompanySanctionMatchRemoved") CompanySanctionMatchRemoved,
	@SerializedName("CompanyNameChanged") CompanyNameChanged,
	@SerializedName("CompanyDescriptionChanged") CompanyDescriptionChanged,
	@SerializedName("CompanySignatoryChanged") CompanySignatoryChanged,
	@SerializedName("CompanyLinkAdded") CompanyLinkAdded,
	@SerializedName("CompanyLinkRemoved") CompanyLinkRemoved,
	@SerializedName("CompanyLinkUpdated") CompanyLinkUpdated,
	@SerializedName("CompanyLinkScreeningMatched") CompanyLinkScreeningMatched,
	@SerializedName("CompanyLinkScreeningMatchRemoved") CompanyLinkScreeningMatchRemoved,
	@SerializedName("CompanyLinkScreeningMatchedNameChanged") CompanyLinkScreeningMatchedNameChanged,
	@SerializedName("CompanyScreeningMatched") CompanyScreeningMatched,
	@SerializedName("CompanyScreeningMatchRemoved") CompanyScreeningMatchRemoved,
	@SerializedName("CompanyAddressChanged") CompanyAddressChanged,
	@SerializedName("CompanySniClassificationChanged") CompanySniClassificationChanged,
}

