package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class WebhookDeliveryFailure 
{
	public Object body;
	public String id;
	public Object metadata;
	public boolean onCreated;
	public String reason;
	public String referenceId;
	public Date timestamp;
	public String type;
}

