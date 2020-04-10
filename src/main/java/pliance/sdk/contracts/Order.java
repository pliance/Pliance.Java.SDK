package pliance.sdk.contracts;

import java.util.Date;

import pliance.sdk.contracts.responses.Response;
import pliance.sdk.contracts.responses.ResponseGeneric;

import com.google.gson.annotations.SerializedName;

public enum Order
{
	@SerializedName("Any") Any,
	@SerializedName("Strict") Strict,
	@SerializedName("Exact") Exact,
}

