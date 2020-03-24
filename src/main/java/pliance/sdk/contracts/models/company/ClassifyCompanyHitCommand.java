package pliance.sdk.contracts.models.company;

import pliance.sdk.contracts.models.ClassificationType;

public class ClassifyCompanyHitCommand {
	public String companyReferenceId;
	public String matchId;
	public String aliasId;
	public ClassificationType classification;
}
