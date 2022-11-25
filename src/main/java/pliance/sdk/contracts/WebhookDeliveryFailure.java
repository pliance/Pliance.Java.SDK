package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

public class WebhookDeliveryFailure 
{
	public String id;
	public String reason;
	public String referenceId;
	public Date timestamp;
	public String type;
}

