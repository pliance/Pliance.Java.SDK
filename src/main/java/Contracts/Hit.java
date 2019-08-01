package Contracts;

import java.util.List;

public class Hit {
	public String MatchId;
	public List<TextMatch> MatchedFirstName;
	public List<TextMatch> MatchedLastName;
	public Birthdate Birthdate;
	public boolean IsPep;
	public boolean IsRca;
	public boolean IsSanction;
	public String FirstName;
	public String LastName;
	public ClassificationType Classification;
	public String AliasId;
}
