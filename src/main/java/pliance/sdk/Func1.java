package pliance.sdk;

import pliance.sdk.exceptions.PlianceApiException;

@FunctionalInterface
public interface Func1<P1, RET, E extends Exception> {
	RET accept(P1 p1) throws E;
}