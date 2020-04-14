package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class RegisterPersonResponse extends ResponseGeneric<ViewPersonResponseData> 
{
	public PersonHit[][] hits;
}

