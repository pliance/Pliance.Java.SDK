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
	@SerializedName("ExternalDeputyCeo") ExternalDeputyCeo,
	@SerializedName("SubstituteAccountant") SubstituteAccountant,
	@SerializedName("NonCertifiedSubstituteAccountant") NonCertifiedSubstituteAccountant,
	@SerializedName("Liquidator") Liquidator,
	@SerializedName("SubstituteLiquidator") SubstituteLiquidator,
	@SerializedName("Procurator") Procurator,
	@SerializedName("KeyPerson") KeyPerson,
	@SerializedName("PersonOfNotification") PersonOfNotification,
	@SerializedName("Owner") Owner,
	@SerializedName("NonCertifiedAccountant") NonCertifiedAccountant,
	@SerializedName("DeputyCeo") DeputyCeo,
	@SerializedName("Actuary") Actuary,
	@SerializedName("SubstituteChairman") SubstituteChairman,
	@SerializedName("SubstituteCeo") SubstituteCeo,
	@SerializedName("Complimentary") Complimentary,
	@SerializedName("LimitedPartnerOwner") LimitedPartnerOwner,
	@SerializedName("Director") Director,
	@SerializedName("Founder") Founder,
	@SerializedName("Unknown") Unknown,
}

