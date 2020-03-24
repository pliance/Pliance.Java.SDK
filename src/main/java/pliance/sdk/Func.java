package pliance.sdk;

@FunctionalInterface
public interface Func<P1, RET, E extends Throwable> {
	RET accept(P1 p1) throws E;
}