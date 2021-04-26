package pliance.sdk.contracts;

import java.util.Date;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;
import com.google.gson.annotations.SerializedName;

public enum Fuzziness
{
	@SerializedName("Metaphone") Metaphone,
	@SerializedName("Simple") Simple,
	@SerializedName("Metaphone3") Metaphone3,
}

