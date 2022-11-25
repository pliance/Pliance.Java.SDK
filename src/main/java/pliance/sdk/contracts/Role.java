package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;
import com.google.gson.annotations.SerializedName;

public enum Role
{
	@SerializedName("Chairman") Chairman,
	@SerializedName("Ceo") Ceo,
	@SerializedName("BoardMember") BoardMember,
	@SerializedName("LeadAccountant") LeadAccountant,
	@SerializedName("AlternateMember") AlternateMember,
	@SerializedName("ExternalSignatory") ExternalSignatory,
	@SerializedName("Accountant") Accountant,
	@SerializedName("ExternalCeo") ExternalCeo,
}

