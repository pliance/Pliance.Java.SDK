package pliance.sdk;

@FunctionalInterface
public interface Func<T, E extends Exception> {
	T accept() throws E;
}
