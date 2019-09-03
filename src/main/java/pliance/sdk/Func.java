package pliance.sdk;

import pliance.sdk.exceptions.PlianceApiException;

@FunctionalInterface
public interface Func<T, E extends Exception> {
	T accept() throws E;
}
