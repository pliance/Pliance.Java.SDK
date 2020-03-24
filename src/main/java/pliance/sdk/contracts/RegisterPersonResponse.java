package pliance.sdk.contracts;

import pliance.sdk.contracts.models.Hit;
import pliance.sdk.contracts.models.ViewPersonResponseData;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class RegisterPersonResponse extends ResponseGeneric<ViewPersonResponseData> {
	public Hit[][] hits;
}
