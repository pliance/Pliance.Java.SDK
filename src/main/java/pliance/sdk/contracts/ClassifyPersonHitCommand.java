package pliance.sdk.contracts;

import pliance.sdk.contracts.models.ClassificationType;

public class ClassifyPersonHitCommand {
	public String personReferenceId;
	public String matchId;
	public String aliasId;
	public ClassificationType classification;
}
