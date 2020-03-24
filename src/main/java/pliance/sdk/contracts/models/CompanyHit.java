package pliance.sdk.contracts.models;

import java.util.List;

public class CompanyHit {
	public String matchId;
	public String aliasId;
	public boolean isSanction;
	public ClassificationType classification;
	public String name;
	public List<TextMatch> matchedName;
}
