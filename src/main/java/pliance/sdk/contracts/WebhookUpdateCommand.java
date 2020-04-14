package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class WebhookUpdateCommand 
{
	public boolean enabled;
	public String secret;
	public String url;
}

