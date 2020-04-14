package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;
import com.google.gson.annotations.SerializedName;

public enum ClassificationType
{
	@SerializedName("Unknown") Unknown,
	@SerializedName("FalsePositive") FalsePositive,
	@SerializedName("Match") Match,
}

