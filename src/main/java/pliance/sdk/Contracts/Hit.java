package pliance.sdk.Contracts;

import java.util.List;

public class Hit {
	public String matchId;
	public List<TextMatch> matchedFirstName;
	public List<TextMatch> matchedLastName;
	public Birthdate birthdate;
	public boolean isPep;
	public boolean isRca;
	public boolean isSanction;
	public String firstName;
	public String lastName;
	public ClassificationType classification;
	public String aliasId;
}
