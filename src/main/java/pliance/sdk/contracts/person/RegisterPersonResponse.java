package pliance.sdk.contracts.person;

import pliance.sdk.contracts.models.PersonHit;
import pliance.sdk.contracts.models.ViewPersonResponseData;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class RegisterPersonResponse extends ResponseGeneric<ViewPersonResponseData> {
	public PersonHit[][] hits;
}
