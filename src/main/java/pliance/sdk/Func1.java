package pliance.sdk;

@FunctionalInterface
public interface Func1<P1, RET, E extends PlianceApiException> {
	RET accept(P1 p1) throws E;
}